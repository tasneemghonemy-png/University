package com.sci.dao;

import com.sci.config.DBConfig;
import com.sci.models.Passport;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBPassport {
    public void insert(Passport pass){
        Transaction tx = null;
        try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
            tx = s.beginTransaction();
            s.save(pass);
            tx.commit();
        }catch (Exception e){
            if(tx != null)tx.rollback();
            System.out.println("EXception from insert passport");
            e.printStackTrace();
        }
    }
}
