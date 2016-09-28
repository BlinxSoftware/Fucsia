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

    private String password;

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage msg = null;

    public LupaBean() {
    }

    public Magnifyingglass getMagnifyingglas() {
         if (magnifyingglas == null) {
            magnifyingglas = new Magnifyingglass();
        }
        return magnifyingglas;
    }

    public void setMagnifyingglas(Magnifyingglass magnifyingglas) {
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

}
