package com.example.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TransferEventListener implements ApplicationListener<TransferEvent> {
    @Override
    public void onApplicationEvent(TransferEvent event) {
        //The code to execute on the event
        System.out.println("Transfer event received: ");
        System.out.println(event.getTransferDTO());
        System.out.println(event.getSource());
    }
}
