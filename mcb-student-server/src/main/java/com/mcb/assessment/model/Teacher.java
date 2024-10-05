package com.mcb.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teacherId;

    @ManyToOne
    @JoinColumn(name="subjectId")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
}
