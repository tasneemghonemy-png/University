package com.sci.models;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employe")
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_seq")
    @SequenceGenerator(name = "employe_seq",sequenceName = "emp_seq",allocationSize = 1)

    @Column(name = "employe_id")
    private Integer employeId;

    @Column(name = "employe_name")
    private String employeName;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "is_active")
    private int is_active;

    @Column(name = "salar")
    private Double salar;

    //employee  , pass
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id")
    Passport EmployePassport;

    //emp,proj
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "emp_project",
            joinColumns = @JoinColumn(name = "employeId"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projectList;

    @Override
    public String toString() {
        return "Employe{" +
                "employeId=" + employeId +
                ", employeName='" + employeName + '\'' +
                ", hireDate=" + hireDate +
                ", is_active=" + is_active +
                ", salar=" + salar+
                '}';
    }
}
