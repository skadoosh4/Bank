package com.example.demo.event;


import com.example.demo.transaction.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TransferEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TransferEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(Object source , TransferDTO transferDTO){
        TransferEvent transferEvent = new TransferEvent(source , transferDTO);
        applicationEventPublisher.publishEvent(transferEvent);
    }
}
