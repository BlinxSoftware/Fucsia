/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Daos.InterfaceUser;
import Daos.UserDao;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import Modelo.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import util.HibernateUtil;

/**
 *
 * @author Federico
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    private User user;
    private User user1;
    private List<User> users;
    private List<User> usuariosFilter;
    private long idPerfilSeleccionado;
  
    private String password;
    

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage msg = null;
    private String userName;

    public UsuarioBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

//    public List<Usuario> getUsuariosFilter() {
//        return usuariosFilter;
//    }
//
//    public void setUsuariosFilter(List<Usuario> usuariosFilter) {
//        this.usuariosFilter = usuariosFilter;
//    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public void prepararUsuario(Long id) {
//        InterfaceUsuario dao = new UsuarioDao();
//        usuario = dao.buscarPorId(id);
//    }
//
    public void actualizarUsuario(ActionEvent actionEvent) {
        System.out.println("Lalalla");
        if (user1 != null) {
            InterfaceUser dao = new UserDao();
            dao.actualizar(user1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados correctamente ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            user1 = new User();
        }

    }

    public void login(ActionEvent actionEvent) {
        if (userName != null && password != null) {
            RequestContext context1 = RequestContext.getCurrentInstance();
            boolean loggedIn = false;
            InterfaceUser dao = new UserDao();
            user = dao.buscarPorUsuario(userName, password);

            if (user != null) {
                loggedIn = true;
                 msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Usuario correcto");
            } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario Incorrecto");
            }

           
           FacesContext.getCurrentInstance().addMessage(null, msg);
            context1.addCallbackParam("loggedIn", loggedIn);

        }
    }

    public User getUser1() {
        if (user1 == null) {
            user1 = new User();
        }
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {

        InterfaceUser dao = new UserDao();
        users = dao.buscarTodos();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    public void prepararAdicionarUsuario(ActionEvent actionEvent) {
//        usuario1 = new Usuario();
//        idPerfilSeleccionado = 0;
//
//    }
    public void adicionar(ActionEvent actionEvent) {
        System.out.println("Entro");
        if (user1 != null) {
            try {
                InterfaceUser dao = new UserDao();
                dao.salvar(user1);
                user1 = new User();
            } catch (Exception e) {
                e.printStackTrace();
            }
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado exitosamente.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }
//    public void prepararEliminarUsuario(Long id) {
//        if (id > 0) {
//            InterfaceUsuario dao = new UsuarioDao();
//            usuario = dao.buscarPorId(id);
//        }
//    }
//

    public void eliminar() {
        if (user1 != null) {
            InterfaceUser dao = new UserDao();
            dao.remover(user1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario borrado exitosamente.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public long getIdPerfilSeleccionado() {
        return idPerfilSeleccionado;
    }

    public void setIdPerfilSeleccionado(long idPerfilSeleccionado) {
        this.idPerfilSeleccionado = idPerfilSeleccionado;
    }

    // public void cerrarSession(UsuarioBean usuarioBean, SuperCajaBean superCajaBean) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        //HibernateUtil.shutdown();
//        
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        usuarioBean.setUsuario(null);
//        superCajaBean.setSuperCaja(null);
//        superCajaBean.superCajaLogin.clear();
//        usuario = new Usuario();
    // }
    public void limpiarUser() {
        user1 = new User();

    }
}
