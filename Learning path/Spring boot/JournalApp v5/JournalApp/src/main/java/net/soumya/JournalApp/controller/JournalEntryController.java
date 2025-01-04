package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.services.JournalEntryService;
import net.soumya.JournalApp.services.UsersServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryServices;

    @Autowired
    private UsersServices usersServices;

    // get data by username
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = usersServices.findByUserName(username);
        try {
            if (user != null) {
                List<JournalEntry> journalEntries = user.getJournalEntries();
                if (!journalEntries.isEmpty()) {
                    return new ResponseEntity<>(journalEntries, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
            return new ResponseEntity<>(username + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // enter data
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            if (usersServices.findByUserName(username) != null) {
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try {
            Users user = usersServices.findByUserName(username);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            // Filter the journal entries for the given ID
            boolean entryExists = user.getJournalEntries().stream().anyMatch(entry -> entry.getId().equals(id));

            if (entryExists) {
                Optional<JournalEntry> journalEntry = journalEntryServices.getById(id);
                if (journalEntry.isPresent()) {
                    return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Journal entry not found for user: " + username, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // delete by id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            if (usersServices.findByUserName(username) != null) {
                JournalEntry entry = journalEntryServices.getById(id).orElse(null);
                if (entry != null) {
                    journalEntryServices.deleteById(id, username);
                    return new ResponseEntity<>(entry + "\nDelete this entry", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(id + " not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update by id
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        try {
            Users user = usersServices.findByUserName(username);
            if (user != null) {
                boolean entryExists = user.getJournalEntries().stream().anyMatch(entry -> entry.getId().equals(id));
                if (entryExists) {
                    Optional<JournalEntry> journalEntry = journalEntryServices.getById(id);
                    if (journalEntry.isPresent()) {
                        JournalEntry entry = journalEntry.get();
                        entry.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : entry.getTitle());
                        entry.setContent(!newEntry.getContent().isEmpty() ? newEntry.getContent() : entry.getContent());
                        journalEntryServices.saveEntry(entry);
                        return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Journal entry not found for user: " + username, HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(username, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
