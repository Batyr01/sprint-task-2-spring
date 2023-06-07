package com.projecct.sprinttask2.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request")
@Getter
@Setter
public class RequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    String userName;

    @Column(name = "courseName")
    String courseName;

    @Column(name = "commentary")
    String commentary;

    @Column(name = "phone")
    String phone;

    @Column(name = "handled")
    boolean handled;



}
