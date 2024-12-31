package com.example.demo.transaction;


import com.example.demo.event.TransferEventPublisher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TransferService {

    private final BankAccountRepository bankAccountRepository;
    private final TransferEventPublisher transferEventPublisher;

    @Autowired
    public TransferService(BankAccountRepository bankAccountRepository , TransferEventPublisher transferEventPublisher) {
        this.bankAccountRepository = bankAccountRepository;
        this.transferEventPublisher = transferEventPublisher;
    }

    public ResponseEntity transfer(TransferDTO transferDTO){
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transferDTO.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transferDTO.getToUser());

        if(fromAccount.isEmpty() || toAccount.isEmpty()){
            throw new RuntimeException("User not found");
        }

        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        //add & deduct
        add(to , transferDTO.getAmount());

        deduct(from , transferDTO.getAmount());

        transferEventPublisher.publish(this , transferDTO);

        return ResponseEntity.ok().body("Transfer successful");
    }

    private void deduct(BankAccount bankAccount , Double amount){
        if (amount > bankAccount.getBalance()){
            throw new RuntimeException("Insufficient funds");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    private void add(BankAccount bankAccount, Double amount){
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
