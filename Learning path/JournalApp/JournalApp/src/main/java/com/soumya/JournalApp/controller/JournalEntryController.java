package com.soumya.JournalApp.controller;

import com.soumya.JournalApp.entity.JournalEntry;
import com.soumya.JournalApp.services.JournalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalServices journalServices;

    //get all
    @GetMapping
    public ResponseEntity<?> getAllJournalEntries() {

    }

    // set a new entry
    @PostMapping
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry journalEntry) {

    }

    // get by id
    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String id) {

    }

    // update by id
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable(name = "id") String id, @RequestBody JournalEntry newEntry) {

    }


    // delete by id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String id) {

    }
}
