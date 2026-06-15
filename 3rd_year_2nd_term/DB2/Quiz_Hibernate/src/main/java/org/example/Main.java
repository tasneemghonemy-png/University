package org.example;

import com.sci.config.DBConfig;
import com.sci.dao.Condition;
import com.sci.dao.DBEmploye;
import com.sci.dao.DBPassport;
import com.sci.dao.DBProject;
import com.sci.models.Employe;
import com.sci.models.Passport;
import com.sci.models.Project;
import org.hibernate.Session;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sci.dao.Condition.Operator.*;

public class Main {
    public static void main(String[] args) {
        // ************************ test 1s tLevel cache ************************
        try (Session s = DBConfig.SESSION_FACTORY.openSession()) {
            Employe e1 = s.get(Employe.class, 1);
            Employe e2 = s.get(Employe.class, 1);
            System.out.println(e1 == e2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ************************  print projects and passports ************************
            /*try (Session s = DBConfig.SESSION_FACTORY.openSession()) {
                List<Employe>  employeList =  s.createQuery("FROM Employe", Employe.class).list();
                for (Employe e : employeList){
                    System.out.println(e.getEmployePassport());
                    System.out.println(e.getProjectList());
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }*/
        //************************ pagination test ************************
        /*DBEmploye dbemp = new DBEmploye();
        List<Employe> emps = dbemp.pagination(1,2);
        for (Employe e: emps){
            System.out.println(e.getEmployeName());
        }*/
        //************************ Criteria query test ************************

        /*Condition c1 = new Condition("employeName","a%",LIKE);
        Condition c2 = new Condition("hireDate",new Date("1-Jan-2015"),GT);
        Condition c3 = new Condition("salar",10000,GT);
        Condition c4 = new Condition("is_active",1,EQ);

        List<Condition>conditions = new ArrayList<>();
        conditions.add(c1);
        conditions.add(c2);
        conditions.add(c3);
        conditions.add(c4);
        DBEmploye dbEmploye = new DBEmploye();
        List<Employe> employeList = dbEmploye.getEmploye(conditions);
        System.out.println(employeList.size());
        for (Employe e : employeList){
            System.out.println(e.getEmployeName());
        }*/

//        Passport pass1 = new Passport();
//        pass1.setPassportNo("B1234567");
//        pass1.setCountry("canada");
//        pass1.setPassportId(1);
//        DBPassport dbPassport = new DBPassport();
//        dbPassport.insert(pass1);
//
//        DBProject dbProject = new DBProject();
//        List<Project> projectList = new ArrayList();
//        Project project1 = new Project();
//        project1.setTitle("Erp3");
//        project1.setBudget(5555.0);
////
//        Project project2 = new Project();
//        project2.setTitle("Erp5");
//        project2.setBudget(7777.0);
//
//        Project project3 = new Project();
//        project3.setTitle("Erp4");
//        project3.setBudget(4442.0);
//
//        dbProject.insert(project1);
//        dbProject.insert(project2);
//
//        projectList.add(project1);
//        projectList.add(project2);
//        projectList.add(project3);

  /*      try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
            Passport p = s.get(Passport.class,2);
            List<Project> projectList = new ArrayList<>();
            projectList.add(s.get(Project.class,3));
            projectList.add(s.get(Project.class,4));
            projectList.add(s.get(Project.class,5));

            Employe e1 = new Employe();
            e1.setEmployeName("Kamal");
            e1.setHireDate(new Date("1-Jan-2020"));
            e1.setSalar(90000.0);
            e1.setIs_active(1);
            e1.setProjectList(projectList);

            DBEmploye dbEmploye = new DBEmploye();
            dbEmploye.insert(e1);

        }catch (Exception e){
            e.printStackTrace();
        }
*/
//try(Session s = DBConfig.SESSION_FACTORY.openSession()) {
//    Passport p = s.get(Passport.class,2);
//    System.out.println(p.getCountry());
//    System.out.println(s.get(Employe.class,1).getEmployePassport().getPassportId());
////    System.out.println();
////    System.out.println(s.get(Employe.class,3).getEmployePassport().getPassportId());
//}
//
    }
}
