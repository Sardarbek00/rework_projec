package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DROP User",User.class);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
//        try (Session session = Util.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            User user = session.get(User.class, id);
//            if (user != null) {
//                // Вывод информации о пользователе перед его удалением
//                System.out.println("Информация о пользователе перед удалением:");
//                System.out.println("Имя: " + user.getName());
//                System.out.println("Фамилия: " + user.getLastName());
//                System.out.println("Возраст: " + user.getAge());
//
//                // Удаление пользователя из базы данных
//                session.remove(user);
//                System.out.println("Пользователь успешно удален.");
//            } else {
//                System.out.println("Пользователь с идентификатором " + id + " не найден.");
//            }
//            session.getTransaction().commit();
//        } catch (HibernateException e) {
//            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
//        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        String query;
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
