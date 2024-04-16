package peaksoft.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import peaksoft.model.User;

public class Util {
    // реализуйте настройку соеденения с БД
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("ERROR in getSessionFactory method: ");
             // Это позволит вам увидеть полный стек ошибок
        }
        return sessionFactory;
    }
}
