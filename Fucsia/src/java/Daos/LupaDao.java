/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.util.List;
import Modelo.Magnifyingglass;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

/**
 *
 * @author Federico
 */
public class LupaDao implements InterfaceLupa {

    @Override
    public Magnifyingglass buscarPorLupa(String username, String password) {

        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        Magnifyingglass magnifyingglass;
        try {

            magnifyingglass = (Magnifyingglass) sess.createQuery("select u from User u where userName='" + username.trim() + "' and password='" + password.trim() + "'").uniqueResult();
            tx.commit();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return magnifyingglass;

    }

    @Override
    public void actualizar(Magnifyingglass magnifyingglass) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.merge(magnifyingglass);
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
    public List<Magnifyingglass> buscarTodos() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        List<Magnifyingglass> magnifyingglass;
        try {
            magnifyingglass = sess.createQuery("from Magnifyingglass m order by m.name asc ").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return magnifyingglass;
    }

    @Override
    public List<Magnifyingglass> buscarTodosXLupa(Long id) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        List<Magnifyingglass> magnifyingglass;
        try {
            magnifyingglass = sess.createQuery("from User u where u.magnifyingglass.id='" +id+ "' order by u.level asc").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return magnifyingglass;
    }

    @Override
    public void salvar(Magnifyingglass magnifyingglass) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.save(magnifyingglass);
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
    public void remover(Magnifyingglass magnifyingglass) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(magnifyingglass);
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
    public Magnifyingglass buscarPorId(Long id) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sess.beginTransaction();
        Magnifyingglass magnifyingglass;
        try {
            magnifyingglass = (Magnifyingglass) sess.get(Magnifyingglass.class, id);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
        return magnifyingglass;
    }
}
