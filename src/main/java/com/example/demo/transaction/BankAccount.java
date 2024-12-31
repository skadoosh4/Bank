package com.example.demo.transaction;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank")
public class BankAccount {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private double balance;

}
