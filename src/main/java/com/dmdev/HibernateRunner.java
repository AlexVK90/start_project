package com.dmdev;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
    public static void main(String[] args) {
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            //User user = User.builder()
             //       .name("Иван");
            User newUser = new User();
            newUser.setAge(Byte.valueOf("6"));
            newUser.setName("name");
            newUser.setLastName("lastName");
            newUser.setId(5L);
            //newUser.toString();
            System.out.println(newUser.toString());

            session.save(newUser);
        }



    }
}
