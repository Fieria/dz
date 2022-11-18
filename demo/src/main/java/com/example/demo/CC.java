package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchControls;
import java.util.*;

@RestController
public class CC
{
    private final List<Contact> contacts = new ArrayList<Contact>();

    // curl -X POST -H "Content-Type: application/json" -d '{"name":"Vlad", "number":"239", "email": "239@gmail.com"}' http://localhost:8080/contacts
    @PostMapping("contacts1")
    public ResponseEntity<Void> addContact(@RequestBody Contact contact)
    {
        contacts.add(contact);
        return ResponseEntity.accepted().build();
    }

    }