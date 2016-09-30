/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Daos.InterfaceUser;
import Daos.UserDao;
import Daos.InterfaceLupa;
import Daos.LupaDao;
import Modelo.Profile;
import Modelo.Magnifyingglass;
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
public class LupaBean implements Serializable {

    private Magnifyingglass magnifyingglas;
    private User user1;
    private List<Magnifyingglass> magnifyingglass;
    private List<User> usuariosFilter;
    private long idPerfilSeleccionado;
    private boolean estadoGuardar = false;
    private UploadedFile file;
    private Date dia = new Date();
     private TreeNode root;

    private String password;

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage msg = null;

    public LupaBean() {
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Magnifyingglass getMagnifyingglas() {
         if (magnifyingglas != null) {
             System.out.println("lalallaa: "+magnifyingglas.getId());
         }
        
         if (magnifyingglas == null) {
            magnifyingglas = new Magnifyingglass();
            
        }
         
        return magnifyingglas;
    }

    public void setMagnifyingglas(Magnifyingglass magnifyingglas) {
         if (magnifyingglas != null) {
             System.out.println("lalallaase: "+magnifyingglas.getId());
         }
        
        this.magnifyingglas = magnifyingglas;
    }

    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public void actualizarUsuario(ActionEvent actionEvent) {
        if (user1 != null) {
            InterfaceUser dao = new UserDao();
            dao.actualizar(user1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos actualizados correctamente ", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            user1 = new User();
        }

    }

    public List<Magnifyingglass> buscarTodos() {
        InterfaceLupa dao = new LupaDao();
        magnifyingglass = dao.buscarTodos();
        return magnifyingglass;
    }

    public void adicionar(ActionEvent actionEvent) {
        if (user1 != null) {
            try {

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

    public void eliminar() {
        if (user1 != null) {
            InterfaceUser dao = new UserDao();
            user1.setDateD(dia);
            dao.actualizar(user1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario borrado exitosamente.", null);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void limpiarUser() {
        user1 = new User();

    }
    public void verLupa(Long id) {
        
         System.out.println("id> "+id);
         System.out.println("id1> "+magnifyingglas.getId());
        InterfaceUser dao = new UserDao();
        List<User> users = dao.buscarTodosXLupa(Long.parseLong("1"));
        System.out.println("users> "+users);
        root = new DefaultTreeNode(users.get(0).getName(), null);
        TreeNode node0 = new DefaultTreeNode(users.get(1).getUserName(), root);
        TreeNode node1 = new DefaultTreeNode(users.get(2).getUserName(), root);
        
        TreeNode node00 = new DefaultTreeNode(users.get(3).getUserName(), node0);
        TreeNode node01 = new DefaultTreeNode(users.get(4).getUserName(), node0);
        
        TreeNode node10 = new DefaultTreeNode(users.get(5).getUserName(), node1);
        TreeNode node11 = new DefaultTreeNode(users.get(6).getUserName(), node1);
        
        node00.getChildren().add(new DefaultTreeNode(users.get(7).getUserName()));
        node00.getChildren().add(new DefaultTreeNode(users.get(8).getUserName()));
        node01.getChildren().add(new DefaultTreeNode(users.get(9).getUserName()));
        node01.getChildren().add(new DefaultTreeNode(users.get(10).getUserName()));
        
        node10.getChildren().add(new DefaultTreeNode(users.get(11).getUserName()));
        node10.getChildren().add(new DefaultTreeNode(users.get(12).getUserName()));
        node11.getChildren().add(new DefaultTreeNode(users.get(13).getUserName()));
        node11.getChildren().add(new DefaultTreeNode(users.get(14).getUserName()));
        
        root.setExpanded(true);
        node0.setExpanded(true);
        node1.setExpanded(true);
        node00.setExpanded(true);
        node01.setExpanded(true);
        node10.setExpanded(true);
        node11.setExpanded(true);
        
    }

}
