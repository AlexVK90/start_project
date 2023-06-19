package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.repository.UserRepository;
import jm.task.core.jdbc.repository.UserRepositoryHibernateImpl;
import jm.task.core.jdbc.repository.UserRepositoryJDBCImpl;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserRepository userRepository = new UserRepositoryHibernateImpl();
        //System.out.println(userRepository.getUserById(2L));
        //System.out.println(userRepository.getAllUsers());
        //userRepository.removeUserById(4l);
        //userRepository.cleanUsersTable();
        //userRepository.saveUser("Семен", "Исаев", (byte) 7);
        //userRepository.saveUser("Иван", "Иванов", (byte) 9);
        //userRepository.dropUsersTable();
        //userRepository.createUsersTable();
        //userRepository.updateAgeByUserId((byte) 14, 2);
    }
}
