package jm.task.core.jdbc.repository;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserRepositoryHibernateImpl implements UserRepository {
    public UserRepositoryHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }

    @Override
    public void editCurrentRow(int i) throws SQLException {

    }

    @Override
    public boolean updateAgeByUserId(byte newAge, int id) {
        return false;
    }
}
