package com.sci.dao;

import com.sci.config.DBConfig;
import com.sci.models.Passport;
import com.sci.models.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBProject {
    public void insert(Project project){
        Transaction tx = null;
        try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
            tx = s.beginTransaction();
            s.save(project);
            tx.commit();
        }catch (Exception e){
            if(tx != null)tx.rollback();
            System.out.println("EXception from insert project");
            e.printStackTrace();
        }
    }
}
