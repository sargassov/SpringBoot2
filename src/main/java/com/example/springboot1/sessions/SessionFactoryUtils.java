package com.example.springboot1.sessions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


public class SessionFactoryUtils {

    private static SessionFactory factory;

    public SessionFactoryUtils(){
        factory = new Configuration()
                .configure("src/main/resources/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    public void shutdown(){
        if(factory != null){
            factory.close();
        }
    }
}
