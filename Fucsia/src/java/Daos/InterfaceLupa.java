/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Modelo.Magnifyingglass;
import java.util.List;

/**
 *
 * @author Federico
 */
public interface InterfaceLupa {

    public Magnifyingglass buscarPorLupa(String username,String password);

    public void actualizar(Magnifyingglass magnifyingglass);

    public List<Magnifyingglass> buscarTodos();
    
    public List<Magnifyingglass> buscarTodosXLupa(Long id);

    public void salvar(Magnifyingglass user);

    public void remover(Magnifyingglass user);

    public Magnifyingglass buscarPorId(Long id);
}
