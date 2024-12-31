package com.example.demo.sse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SSE {

    @GetMapping("/sse")
    public SseEmitter sendSSE(){
        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
           try{
              for(int i=0 ; i<5 ; i++){
                  emitter.send(SseEmitter.event().data("Number = " + i));
              }
           } catch (Exception e) {
               emitter.completeWithError(e);
           }
        });
        return emitter;
    }
}
