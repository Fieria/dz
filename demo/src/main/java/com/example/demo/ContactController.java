package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ContactController {
    private final List<Contact> contacts = new ArrayList<Contact>();

    //curl -X POST -H "Content-Type: application/json" -d '{"name":"Polina", "number": "****", "email": "***@gmail.com"}' http://localhost:8080/contacts
    @PostMapping("contacts")
    public ResponseEntity<Void> addContact(@RequestBody Contact contact) {
        contacts.add(contact);
        return ResponseEntity.accepted().build();
    }

    //curl -X DELETE http://localhost:8080/contacts/{index}
    @DeleteMapping("contacts/{index}")
    public ResponseEntity<Void> deleteContact(@PathVariable("index") Integer index) {
        contacts.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    //curl http://localhost:8080/contacts
    @GetMapping("contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contacts);
    }

    //curl http://localhost:8080/contacts/{index}
    @GetMapping("contacts/{index}")
    public ResponseEntity<Contact> getContact(@PathVariable("index") Integer index) {
        return ResponseEntity.ok(contacts.get(index));
    }

    //curl -X PUT -H "Content-Type: application/json" -d '{"name":"Polina", "number":"***", "email": "***@gmail.com"}' http://localhost:8080/contacts/{index}
    @PutMapping("contacts/{index}")
    public ResponseEntity<Void> updateContact(
            @PathVariable("index") Integer i,
            @RequestBody Contact contact) {
        contacts.remove((int) i);
        contacts.add(i, contact);
        return ResponseEntity.accepted().build();
    }

    //curl -X GET http://localhost:8080/contacts/search/{substring or subnumbers}
    @GetMapping("contacts/search/{substring}")
    public ResponseEntity<List<Contact>> searchContact(@PathVariable("substring") String substring)
    {
        List<Contact> contacts2 = new ArrayList<Contact>();
        for (Contact contact : contacts) {
            if (contact.email.contains(substring) || contact.name.contains(substring) || (contact.number+"").contains(substring))
                contacts2.add(contact);
        }
        return ResponseEntity.ok(contacts2);
    }

    //curl http://localhost:8080/contacts/sort/name
    @GetMapping("contacts/sort/name")
    public ResponseEntity<List<Contact>> sortNameContact()
    {
        ArrayList contacts2 = (ArrayList) contacts;
        Collections.sort(contacts2, new NameComparator());
        return ResponseEntity.ok((List<Contact>)contacts2);
    }

    //curl http://localhost:8080/contacts/sort/number
    @GetMapping("contacts/sort/number")
    public ResponseEntity<List<Contact>> sortNumberContact()
    {
        ArrayList contacts2 = (ArrayList) contacts;
        Collections.sort(contacts2, new NumberComparator());
        return ResponseEntity.ok((List<Contact>)contacts2);
    }

    //curl http://localhost:8080/contacts/sort/email
    @GetMapping("contacts/sort/email")
    public ResponseEntity<List<Contact>> sortEmailContact()
    {
        ArrayList contacts2 = (ArrayList) contacts;
        Collections.sort(contacts2, new EmailComparator());
        return ResponseEntity.ok((List<Contact>)contacts2);
    }

    class NameComparator implements Comparator<Contact> {
        public int compare(Contact a, Contact b) {
            return a.name.compareToIgnoreCase(b.name);
        }
    }

    class EmailComparator implements Comparator<Contact> {
        public int compare(Contact a, Contact b) {
            return a.email.compareToIgnoreCase(b.email);
        }
    }
    class NumberComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact a, Contact b) {
            return Integer.compare(a.number, b.number);
        }
    }

}