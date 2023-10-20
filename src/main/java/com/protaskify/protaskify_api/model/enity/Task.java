//package com.protaskify.protaskify_api.model.enity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "task")
//public class Task {
//    //--------------------Attribute--------------------
//    @Id
//    @Column(name = "task_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "task_name", columnDefinition = "NVARCHAR(200)")
//    private String name;
//
//    @Column(columnDefinition = "VARCHAR(15)")
//    private String status;
//
//    @Column(columnDefinition = "NVARCHAR(MAX)")
//    private String feedback;
//
//    @Column(columnDefinition = "VARCHAR(25)")
//    private String priority;
//
//    @Column(columnDefinition = "NVARCHAR(MAX)")
//    private String description;
//
//    @Column(columnDefinition = "DATE")
//    private Date createDate;
//
//    @Column(columnDefinition = "DATE")
//    private Date finishDate;
//
//    @Column(columnDefinition = "INT")
//    private Integer taskIndex;
//
//
//    //--------------------Relationship--------------------
//    @ManyToOne
////    @JsonIgnore
//    @JoinColumn(name = "student_id")
//    private Student student;
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "feature_id", nullable = true)
//    private Feature feature;
//}