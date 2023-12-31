package jm.task.core.jdbc.repository;

import jm.task.core.jdbc.model.User;

import java.security.Key;
import java.sql.*;
import java.util.List;

public class UserRepositoryJDBCImpl implements UserRepository {

    private Connection connection;

    public UserRepositoryJDBCImpl() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/class",  "root", "1234");
    }

    public void createUsersTable() throws SQLException {

        Statement statement = connection.createStatement();

        String query="CREATE TABLE childs(" +
                "ID INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(40) NOT NULL," +
                "last_name VARCHAR(40) NOT NULL," +
                "age INT NOT NULL)";

        statement.executeUpdate(query);

        System.out.println("Таблица создана");


    }

    public void dropUsersTable() throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("DROP TABLE childs");

        System.out.println("Список учеников удален");

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        User newUser = new User();
        newUser.setAge(Byte.valueOf(age));
        newUser.setName(name);
        newUser.setLastName(lastName);
        //newUser.setId(5L);

        String sql = "INSERT INTO childs (NAME, LAST_NAME, age) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);// почему то еще раз создаю только уже preparedStatement

        //stmt.setLong(1,newUser.getId());
        stmt.setString(1, newUser.getName());
        stmt.setString(2, newUser.getLastName());
        stmt.setByte(3, newUser.getAge());

        stmt.executeUpdate();
        System.out.printf("Новый ученик %s %s добавлен в список".formatted(name,lastName));
        System.out.println();

    }

    public void removeUserById(long id) throws SQLException {

        Statement statement = connection.createStatement();

        System.out.printf("%d row(s) deleted", statement.executeUpdate("DELETE FROM childs WHERE Id =%d".formatted(id)));

    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM childs");

        System.out.println("Данные из таблицы");
        while (results.next()) {
            Integer id = results.getInt(1);
            String name = results.getString(2);
            String Lastname = results.getString(3);
            int age = results.getInt(4);
            System.out.println(id + "\t"+ name + "\t" + Lastname + "\t" + age);
        }

        return null;
    }

    public void cleanUsersTable() throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("TRUNCATE childs");

        System.out.println("Список учеников очищен");

    }

    public void editCurrentRow (int id) throws SQLException {
        //System.out.println("Введите id ученика для коррекции данных");
        Statement statement = connection.createStatement();

        System.out.println("Номер столбца для редактирования: 4");
        int count = 2;
        int value;
        switch (count) {
            case 2:
                System.out.println("Введите новые данные для ячейки: 7");
                statement.executeUpdate("UPDATE `class`.`childs` SET `age` = '7' WHERE (`ID` = '1')");
                break;
            case 3:
                break;
            case 4:
                break;
            default:

        }

    }

    @Override
    public boolean updateAgeByUserId(byte newAge, int id) {
        //возвращает false если не удалось выполнить обновление например нет такого пользователя в базе
        //если изменеия сделать получилось должен вернуть true
        return false;
    }

}
