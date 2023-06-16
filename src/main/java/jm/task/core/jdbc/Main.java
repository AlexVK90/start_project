package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.repository.UserRepository;
import jm.task.core.jdbc.repository.UserRepositoryHibernateImpl;
import jm.task.core.jdbc.repository.UserRepositoryJDBCImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
//
//        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//
//        UserRepository userRepository = new UserRepositoryJDBCImpl();
//        userRepository.createUsersTable();
//        userRepository.saveUser("Виталий","Жданов", (byte) 5);
//        userRepository.saveUser("Семен","Хлопов", (byte) 6);
//        userRepository.getAllUsers();
//        userRepository.editCurrentRow(1);
//        userRepository.updateAgeByUserId((byte) 28, 1);
//        userRepository.getAllUsers();
//        //userRepository.cleanUsersTable();
//        //userRepository.dropUsersTable();


        UserRepository userRepository = new UserRepositoryHibernateImpl();
        //userRepository.getUserById(2L);
        userRepository.cleanUsersTable();
    }
}
