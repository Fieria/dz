package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();

    //curl http://localhost:8080/messages
    /*@GetMapping(value = "messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }*/

    // -X GET -H "Content-Type: application/json" -d {text} http://localhost:8080/messages
    @GetMapping(value = "messages")
    public ResponseEntity<List<String>> getMessagesBy(@RequestBody String text) {
        int len = messages.size();
        List<String> mes = messages;
        for (int i = len - 1; i >= 0; i--) {
            if (!mes.get(i).startsWith(text)) mes.remove((int) i);
        }
        return ResponseEntity.ok(mes);
    }

    //curl -X POST -H "Content-Type: application/json" -d {"text"} http://localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }

    //curl http://localhost:8080/messages/{index}
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }

    //curl -X DELETE http://localhost:8080/messages/{index}
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl -X PUT -H "Content-Type: application/json" -d {"text"} http://localhost:8080/messages/{index}
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }

    //curl http://localhost:8080/messages/search/{text}
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> getMessage2(@PathVariable("text") String text) {
        int len = messages.size();
        int i = 0;
        int ind = -1;
        Boolean f = false;
        while ((i < len) && !f) {
            if (messages.get(i).contains(text)) {
                f = true;
                ind = i;
            }
            i++;
        }

        return ResponseEntity.ok(ind);
    }

    //curl http://localhost:8080/messages/count
    @GetMapping("messages/count")
    public ResponseEntity<Integer> getMessagesCount() {
        return ResponseEntity.ok(messages.size());
    }

   //curl -X POST -H "Content-Type: application/json" -d {text} http://localhost:8080/messages/{index}/create
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> addMessageByIndex(@PathVariable("index") Integer index, @RequestBody String text) {
        messages.add(index - 1, text);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/messages/search/{text}
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deleteTextBySearch(@PathVariable("text") String text) {
        int len = messages.size();
        for (int i = len - 1; i >= 0; i--) {
            if (messages.get(i).contains(text)) messages.remove((int) i);
        }
        return ResponseEntity.noContent().build();
    }


}
