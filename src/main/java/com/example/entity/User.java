package com.example.entity;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Setter @Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;
}
