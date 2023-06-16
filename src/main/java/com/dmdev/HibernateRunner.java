package com.dmdev;


import jakarta.persistence.Query;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateRunner {
    private Configuration configuration;

    public static void main(String[] args) {
//        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        long id = 2;

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();
//            User newUser = new User();
//            newUser.setAge(Byte.valueOf("6"));
//            newUser.setName("name");
//            newUser.setLastName("lastName");
//            newUser.setId(13L);
//            //newUser.toString();
//            System.out.println(newUser.toString());
//
//            session.save(newUser);// метод save() — это старый стандарт Hibernate, а метод persist() — это JPA-стандарт

//            public boolean saveUser(User user) {
//                try (Session session = sessionFactory.openSession()) {
//                    Transaction transaction = session.beginTransaction();
//                    session.persist(user);//!!!!persist
//                    transaction.commit();
//                    return true;
//                }
//                catch() {
//                    return false;
//                }
//            }

             System.out.println(session.createQuery("from User where id=%d".formatted(id), User.class).list());

//            public User getUserById(Integer id) {
//                try (Session session = sessionFactory.openSession()) {
//                    User user = session.get(User.class, id);!!!!get
//                    return user;
//                }
//            }
//            public List<User> getUserById(Long id)  {
//
//                try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//                     Session session = sessionFactory.openSession()) {
//                    return session.createQuery("from User where id=%d".formatted(id), User.class).list();
//                }
//            }



            session.getTransaction().commit();
        }






    }






}
