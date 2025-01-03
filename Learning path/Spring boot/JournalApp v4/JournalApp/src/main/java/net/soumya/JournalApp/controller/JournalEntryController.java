package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.services.JournalEntryService;
import net.soumya.JournalApp.services.UsersServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryServices;

    @Autowired
    private UsersServices usersServices;

    // get data by username
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
        Users user = usersServices.getByUserName(username);
        try {
            if (user != null) {
                List<JournalEntry> journalEntries = user.getJournalEntries();
                if (!journalEntries.isEmpty()) {
                    return new ResponseEntity<>(journalEntries, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
            }
            return new ResponseEntity<>(username + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // enter data
    @PostMapping("/username/{username}")
    public ResponseEntity<?> createEntry(@PathVariable String username, @RequestBody JournalEntry journalEntry) {
        try {
            if (usersServices.getByUserName(username) != null) {
                journalEntryServices.saveEntry(journalEntry, username);
                return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(username + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // find by id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId id) {
        try {
            if (journalEntryServices.getById(id).isPresent()) {
                return new ResponseEntity<>(journalEntryServices.getById(id), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete by id
    @DeleteMapping("/username&id/{username}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username) {
        try {
            if (usersServices.getByUserName(username) != null) {
                JournalEntry entry = journalEntryServices.getById(id).orElse(null);
                if (entry != null) {
                    journalEntryServices.deleteById(id, username);
                    return new ResponseEntity<>(entry, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update by id
    @PutMapping("/username&id/{username}/{id}")
    public ResponseEntity<?> updateEntryById(
            @PathVariable ObjectId id,
            @PathVariable String username,
            @RequestBody JournalEntry newEntry
    ) {
        try {
            if (usersServices.getByUserName(username) != null) {
                JournalEntry entry = journalEntryServices.getById(id).orElse(null);
                if (entry != null) {
                    entry.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : entry.getTitle());
                    entry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : entry.getContent());
                    journalEntryServices.saveEntry(entry);
                    return new ResponseEntity<>(newEntry, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(id + " not found", HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(username + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
