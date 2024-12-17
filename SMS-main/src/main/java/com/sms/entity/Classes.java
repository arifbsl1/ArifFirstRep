package com.sms.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Classes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;
    private String className;
    private String roomNo;
    private String studentCapacity;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    
    @ManyToMany
    @JoinTable(
        name = "class_subject",
        joinColumns = @JoinColumn(name = "class_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subjects> subjects;

//    @ManyToOne
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;
    
//    @OneToMany(mappedBy = "classes",cascade = CascadeType.ALL)
//    private List<Grade> grades;
    
    
}
