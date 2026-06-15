package com.sci.dao;

import com.sci.config.DBConfig;
import com.sci.models.Employe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sci.dao.Condition.Operator.*;

public class DBEmploye {
    public void insert(Employe emp){
        Transaction tx = null;
        try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
            tx = s.beginTransaction();
            s.save(emp);
            tx.commit();
        }catch (Exception e){
            if(tx != null)tx.rollback();
            System.out.println("EXception from insert employee");
            e.printStackTrace();
        }
    }
    public List<Employe> getEmploye(List<Condition> conditions){
        try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Employe> cq = cb.createQuery(Employe.class);
            Root<Employe> root = cq.from(Employe.class);

            List<Predicate>predicates =  new ArrayList<>();
            for(Condition cond : conditions){
                switch (cond.getOperator()){
                    case LIKE -> predicates.add(cb.like(cb.lower(root.get(cond.getColumnName())),cond.getValue().toString()));
                    case GT -> predicates.add(cb.greaterThanOrEqualTo(root.get(cond.getColumnName()),cond.getValue()));
                    case EQ -> predicates.add(cb.equal(root.get(cond.getColumnName()),cond.getValue()));
                }
            }
            cq.where(predicates.toArray(new Predicate[0]));
            return s.createQuery(cq).setFirstResult(1).setMaxResults(5).list();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Employe> pagination(int pageNum, int pageSize){
        try(Session s = DBConfig.SESSION_FACTORY.openSession()){
            return s.createQuery("FROM Employe order by employeId ", Employe.class).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).list();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
