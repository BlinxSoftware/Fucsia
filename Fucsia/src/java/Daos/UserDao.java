/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.util.List;
import Modelo.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author Federico
 */
public class UserDao implements InterfaceUser {

    @Override
    public User buscarPorUsuario(String username, String password) {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        User use;
        try {

            use = (User) sess.createQuery("select u from User u where userName='" + username.trim() + "' and password='" + password.trim() + "'").uniqueResult();
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return use;

    }

    @Override
    public void actualizar(User user) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.merge(user);
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
    }

    @Override
    public List<User> buscarTodos() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        List<User> use;
        try {
            use = sess.createQuery("from User u order by u.lastName asc ").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return use;
    }

    @Override
    public void salvar(User user) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.save(user);
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
    }

    @Override
    public void remover(User user) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(user);
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
    }

//    @Override
//    public Usuario buscarPorId(Long id) {
//        Session sess = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = sess.beginTransaction();
//        Usuario use;
//        try {
//            use = (Usuario) sess.get(Usuario.class, id);
//            tx.commit();
//        } catch (RuntimeException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            throw e;
//        } finally {
//            sess.close();
//        }
//        return use;
//    }
}
