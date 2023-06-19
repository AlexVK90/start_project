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

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

        String hql = "CREATE TABLE IF NOT EXISTS childs " +
                "(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age int NOT NULL)";

         session.createNativeQuery(hql).executeUpdate();

         session.getTransaction().commit();
    }
    }



    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS childs").executeUpdate();


        }
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
                //System.out.println("Добавлен новый ученик: " + name);

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
            else {
                System.out.println("id не найден");
            }
            session.getTransaction().commit();
        }


    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();



        List list = session.createQuery("from User").list();  // не знаю почему не видит название таблицы

        session.getTransaction().commit();

        return list;

        }

    @Override
    public void cleanUsersTable() {

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            System.out.println("Список учеников очищен");
            session.getTransaction().commit();
        }

    }

    @Override
    public boolean updateAgeByUserId(byte newAge, int id) {
       try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()){

           session.beginTransaction();
           session.get(User.class, id).setAge(newAge);
           session.getTransaction().commit();
       }
       catch(Exception e){
           return false;

       }
        return true;
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

