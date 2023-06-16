package jm.task.core.jdbc.repository;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class UserRepositoryHibernateImpl implements UserRepository {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public UserRepositoryHibernateImpl() {
////        Configuration configuration = new Configuration();
////        configuration.addAnnotatedClass(User.class);
////        configuration.configure();
//        //sessionFactory = configuration.buildSessionFactory();
//        sessionFactory = (SessionBuilder) new Configuration().configure().buildSessionFactory();


    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = this.sessionFactory.getCurrentSession(); // создаю объект сессию,
        // которая просит фабрику сессий получить текущую сессию
        session.persist(new User(name,lastName,age)); // вызываю в сессии метод сохранения объекта который передал в параметры
        System.out.println("Добавлен новый ученик: " + name);

    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);

        if (user != null) {
            session.remove(user);
            System.out.println(user.getName() + "удален из списка");
        }


    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("DELETE childs ");

        System.out.println("Список учеников очищен");

    }

    @Override
    public void editCurrentRow(int i) throws SQLException {

    }

    @Override
    public boolean updateAgeByUserId(byte newAge, int id) {
        return false;
    }

    @Override
    public List<User> getUserById(Long id) {

        try (Session session = sessionFactory.getCurrentSession()) {
            return session.createQuery("from User where id=%d".formatted(id), User.class).list();
        }
    }
}
