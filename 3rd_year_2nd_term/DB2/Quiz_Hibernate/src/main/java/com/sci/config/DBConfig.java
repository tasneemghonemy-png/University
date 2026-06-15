package com.sci.config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConfig {
    // Built once — expensive operation
    public static final SessionFactory SESSION_FACTORY =
            new Configuration()   // reads hibernate.cfg.xml
                    .configure()      // loads entity mappings
                    .buildSessionFactory();
    public static void shutdown() {
        SESSION_FACTORY.close();  // release JDBC connections
    }
    //Update the price of a book based on its ISBN.
}