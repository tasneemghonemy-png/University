package com.sci.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Passport")
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
    @SequenceGenerator(name = "passport_seq",sequenceName = "pass_seq",allocationSize = 1)
    @Column(name = "passport_id")
    private Integer passportId;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "EmployePassport")
    private Employe employePass;

    @Override
    public String toString() {
        return "Passport{" +
                "passportId=" + passportId +
                ", passportNo='" + passportNo + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
