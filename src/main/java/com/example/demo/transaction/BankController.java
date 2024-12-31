package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

    private final TransferService transferService;

    @Autowired
    public BankController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestBody TransferDTO transferDTO) {
        return transferService.transfer(transferDTO);
    }
}
