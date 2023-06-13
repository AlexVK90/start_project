package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.repository.UserRepository;
import jm.task.core.jdbc.repository.UserRepositoryJDBCImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserRepository userRepository = new UserRepositoryJDBCImpl();
        userRepository.createUsersTable();
        userRepository.saveUser("Виталий","Жданов", (byte) 5);
        userRepository.saveUser("Семен","Хлопов", (byte) 6);
        userRepository.getAllUsers();
        userRepository.editCurrentRow(1);
        userRepository.getAllUsers();
        //userRepository.cleanUsersTable();
        //userRepository.dropUsersTable();

    }
}
