package com.example.demo.transaction;

import lombok.Data;

@Data
public class TransferDTO {
    private String fromUser;
    private String toUser;
    private double amount;
}
