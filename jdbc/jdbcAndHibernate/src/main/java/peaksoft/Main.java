package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate=new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
      // userDaoHibernate.getAllUsers();

    }
}
