/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Daos.InterfaceUser;
import Daos.UserDao;
import Modelo.Profile;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import Modelo.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
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
    private boolean estadoGuardar = false;
    private UploadedFile file;
    private Date dia = new Date();

    private String password;

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage msg = null;
    private String userName;
    private TreeNode root;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);

        TreeNode node00 = new DefaultTreeNode("Node 0.0", node0);
        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);

        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);

        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.0"));
        node00.getChildren().add(new DefaultTreeNode("Node 0.0.1"));
        node01.getChildren().add(new DefaultTreeNode("Node 0.1.0"));
        node10.getChildren().add(new DefaultTreeNode("Node 1.0.0"));
        root.getChildren().add(new DefaultTreeNode("Node 2"));
    }

    public UsuarioBean() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstadoGuardar() {
        return estadoGuardar;
    }

    public void setEstadoGuardar(boolean estadoGuardar) {
        this.estadoGuardar = estadoGuardar;
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
        if (user1 != null) {
            InterfaceUser dao = new UserDao();
            dao.actualizar(user1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados correctamente ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            user1 = new User();
        }

    }

    public void actualizarUsuarioPropio(ActionEvent actionEvent) {
        if (user != null) {
            InterfaceUser dao = new UserDao();
            if (file != null) {
                subir();
                user.setImage(file.getFileName());
            }
            dao.actualizar(user);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados correctamente ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            user1 = new User();
        }

    }

    public void resetEstado() {
        estadoGuardar = false;
    }

    public String resetEstadoMobil() {
        estadoGuardar = false;
        return "pm:create?transition=flip";
    }

    public void setiarImagen(ActionEvent actionEvent) throws Exception {

        FacesContext fctx = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) fctx.getExternalContext().getContext();
        String path = servletContext.getRealPath("/");

        //    file.write(path+ "subidas" + File.separator + user.getImage());
        //  System.out.println("Archivo ruta: "+ path+ "subidas" + File.separator + user.getImage());
//        file.write(path+ "subidas" + File.separator + user.getImage());
    }

    public void login(ActionEvent actionEvent) {
        if (userName != null && password != null) {
            RequestContext context1 = RequestContext.getCurrentInstance();
            boolean loggedIn = false;
            boolean admin = false;
            InterfaceUser dao = new UserDao();
            user = dao.buscarPorUsuario(userName, password);

            if (user != null) {
                loggedIn = true;
                if (user.getProfile().getId() == 1) {
                    admin = true;
                } else {
                    admin = false;
                }
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Usuario correcto");
            } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario Incorrecto");
            }

            FacesContext.getCurrentInstance().addMessage(null, msg);
            context1.addCallbackParam("loggedIn", loggedIn);
            context1.addCallbackParam("admin", admin);

        }
    }

    public void cerrarSession() {

        FacesContext context = FacesContext.getCurrentInstance();
        //HibernateUtil.shutdown();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate();
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
        if (user1 != null) {
            try {
                subir();
                Profile profile = new Profile();
                profile.setId(Long.parseLong("2"));
                InterfaceUser dao = new UserDao();
                user1.setProfile(profile);
                user1.setDateC(dia);
                user1.setImage(file.getFileName());
                dao.salvar(user1);
                estadoGuardar = true;
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
            user1.setDateD(dia);
            dao.actualizar(user1);
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

    public void subir() {
        if (file != null) {
            try {

                copyFile(file.getFileName(), file.getInputstream());

            } catch (IOException e) {

                e.printStackTrace();

            }
        } else {
            FacesMessage message = new FacesMessage("Por favor  ", " seleccione un archivo.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }

    public void copyFile(String fileName, InputStream in) {

        //fede
        FacesContext fctx = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) fctx.getExternalContext().getContext();
        String path = servletContext.getRealPath("/");

        //-----------------------------------------------------------------------------------------------------
        String imagenRuta;
        File Imagen;

        Imagen = new File(path, "subidas" + File.separator + fileName);
        imagenRuta = Imagen.toString();

        try {

            OutputStream out = new FileOutputStream(new File(path, "subidas" + File.separator + fileName));
            int read = 0;

            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {

                out.write(bytes, 0, read);

            }

            in.close();

            out.flush();

            out.close();

            
        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

}
