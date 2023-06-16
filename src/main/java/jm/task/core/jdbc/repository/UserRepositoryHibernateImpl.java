package jm.task.core.jdbc.repository;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserRepositoryHibernateImpl implements UserRepository {


    //private SessionFactory sessionFactory;
    private Configuration configuration;


    public UserRepositoryHibernateImpl() {
        configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {


    }

    @Override
    public void saveUser(String name, String lastName, byte age){
            try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                 Session session = sessionFactory.openSession()) {

                session.beginTransaction();
//                User newUser = new User();
//                newUser.setAge(Byte.valueOf("6"));
//                newUser.setName(name);
//                newUser.setLastName(lastName);
//                //newUser.setId(13L);
                session.persist(new User(name, lastName, age)); // вызываю в сессии метод сохранения объекта который передал в параметры
                System.out.println("Добавлен новый ученик: " + name);

                session.getTransaction().commit();

            }
        }

    public void updateUser(User user) {
        //Session session = this.sessionFactory.getCurrentSession();
        //session.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            User user = (User) session.get(User.class, id);

            if (user != null) {
                session.remove(user);
                System.out.println(user.toString() + " удален из списка");
            }
            session.getTransaction().commit();
        }


    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();



        List list = session.createQuery("from childs").list();  // не знаю почему не видит название таблицы

        session.getTransaction().commit();

        return list;

        }

    @Override
    public void cleanUsersTable() {

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
          //  session.createQuery("delete childs");

            //System.out.println("Список учеников очищен");
        }

    }

    @Override
    public boolean updateAgeByUserId(byte newAge, int id) {
        return false;
    }

    @Override
    public List<User> getUserById(Long id) {
        List list;

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            list = session.createQuery("from User where id=%d".formatted(id), User.class).list();
            session.getTransaction().commit();
        }
            return list;
        }
    }

