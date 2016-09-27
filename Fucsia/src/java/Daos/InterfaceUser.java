/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Modelo.User;
import java.util.List;

/**
 *
 * @author Federico
 */
public interface InterfaceUser {

    public User buscarPorUsuario(String username,String password);

    public void actualizar(User user);

    public List<User> buscarTodos();
    
    public List<User> buscarTodosXLupa(Long id);

    public void salvar(User user);

    public void remover(User user);

//    public Usuario buscarPorId(Long id);
}
