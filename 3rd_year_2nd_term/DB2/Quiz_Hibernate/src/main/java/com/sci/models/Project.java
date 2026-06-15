package com.sci.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name = "project_seq",sequenceName = "proj_seq",allocationSize = 1)

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "title")
    private String title;

    @Column(name = "budget")
    private Double budget;


}
