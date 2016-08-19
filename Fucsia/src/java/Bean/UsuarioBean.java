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
    private List<User> users;
    private List<User> usuariosFilter;
    private long idPerfilSeleccionado;
    private String ip = "http://localhost:8080/Tesoreria/faces/";
    //localhost
    //192.168.2.47
    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage msg = null;
    private String userName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public UsuarioBean() {
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
//    public void actualizarUsuario(UsuarioBean usuarioBean) {
//
//        if (usuario1 != null) {
//            InterfaceUsuario dao = new UsuarioDao();
//            InterfacePerfil daoPerfil = new PerfilDao();
//            Perfil perfil = daoPerfil.buscarPorId(idPerfilSeleccionado);
//            usuario1.setPerfil(perfil);
//            dao.actualizar(usuario1);
//            
//              if (auditoriaCajaBean != null) {
//
//                auditoriaCajaBean.registrarAuditoriaOperacionesUsuario(usuario1, usuarioBean.getUsuario(), "Modificar");
//
//            } else {
//
//                auditoriaCajaBean = new AuditoriaCajaBean();
//                auditoriaCajaBean.registrarAuditoriaOperacionesUsuario(usuario1, usuarioBean.getUsuario(), "Modificar");
//            }
//            
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados correctamente ", null);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            usuario1 = new Usuario();
//        }
//
//    }
//    public void login(Long idUser, Long idSuperCaja, String password, CajaBean cajaBean, SuperCajaBean superCajaBean) {
//        if (idUser > 0 && idSuperCaja > 0) {
//            RequestContext context1 = RequestContext.getCurrentInstance();
//            boolean loggedIn = false;
//            InterfaceSuperCaja daoS = new SuperCajaDao();
//            superCaja = new SuperCaja();
//            superCaja = daoS.buscarPorId(idSuperCaja);
//            InterfaceCaja daoC = new CajaDao();
//
//            superCajaBean.setSuperCaja(superCaja);
//            InterfaceUsuario dao = new UsuarioDao();
//            usuario = new Usuario();
//            usuario = dao.buscarPorId(idUser);
//            usuario = dao.buscarPorUsuario(usuario.getUsername(), password);
//            afiliados = new Afiliados();
//            Caja cajaAux = new Caja();
//            cajaAux = daoC.validarCajaAbierta(usuario.getUsername(), idSuperCaja);
//            if (cajaAux == null) {
//                if (usuario != null) {
//                    loggedIn = true;
//                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario.getUsername());
//                    caja = daoC.BuscarIdCaja(usuario.getUsername(), idSuperCaja);
//                    cajaBean.setCaja(caja);
//                    cajaBean.setSuperCaja(superCaja);
//                    
//                     if (auditoriaCajaBean != null) {
//
//                        auditoriaCajaBean.registrarAuditoriaDeLogin(usuario, superCaja, "Login");
//
//                    } else {
//
//                        auditoriaCajaBean = new AuditoriaCajaBean();
//                        auditoriaCajaBean.registrarAuditoriaDeLogin(usuario, superCaja, "Login");
//                    }
//                    
//                } else {
//                    loggedIn = false;
//                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario Incorrecto");
//                }
//            } else {
//                loggedIn = false;
//                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error", "Hay una Caja abierta,Usuario: " + cajaAux.getUsuario());
//
//            }
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            context1.addCallbackParam("loggedIn", loggedIn);
//
//        }
//    }
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
//    public void adicionar() {
//        if (usuario1 != null) {
//            try {
//                InterfaceUsuario dao = new UsuarioDao();
//                InterfacePerfil daoPerfil = new PerfilDao();
//                Perfil perfil = daoPerfil.buscarPorId(idPerfilSeleccionado);
//                usuario1.setPerfil(perfil);
//                dao.salvar(usuario1);
//                usuario1 = new Usuario();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado exitosamente.", null);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        }
//    }
//    public void prepararEliminarUsuario(Long id) {
//        if (id > 0) {
//            InterfaceUsuario dao = new UsuarioDao();
//            usuario = dao.buscarPorId(id);
//        }
//    }
//
//    public void eliminar() {
//
//        if (usuario1 != null) {
//            InterfaceUsuario dao = new UsuarioDao();
//            dao.remover(usuario1);
//            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario borrado exitosamente.", null);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//
//        }
//    }
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
}
