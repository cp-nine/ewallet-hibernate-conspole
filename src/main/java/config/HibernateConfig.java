package config;

import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
* Hibernate Configuration and Replacement for mysql connection.
*/

public class HibernateConfig {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    public SessionFactory getSessionFactory(){
        // check if session factory is null
        if (factory == null){
            try {
                Configuration config = new Configuration();
                // config.configure();
                config.configure("hibernate.cfg.xml");
                // named query by xml
                config.addResource("named-queries.hbm.xml");

                // add annotatedClass here
                config.addAnnotatedClass(Customer.class);
                config.addAnnotatedClass(Account.class);
                config.addAnnotatedClass(TrxEntity.class);
                config.addAnnotatedClass(Wallet.class);
                config.addAnnotatedClass(WalletAccount.class);
                config.addAnnotatedClass(TrxEntity.class);

                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                factory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return factory;

    }

}
