package net.soumya.JournalApp.controller;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;


    // get all
    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryServices.getAllEntries();
    }

    // enter data
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntryServices.saveEntry(journalEntry);
        return true;
    }

    // find by id
    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryServices.getById(id).orElse(null);
    }

    // Dete by id
    @DeleteMapping("/id/{id}")
    public boolean deleteEntryById(@PathVariable ObjectId id) {
        journalEntryServices.delteById(id);
        return true;
    }

    // update data
    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry entry = journalEntryServices.getById(id).orElse(null);

        if (entry != null) {
            entry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : entry.getTitle());
            entry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : entry.getContent());

        }
        journalEntryServices.updateEntryById(entry);
        return entry;
    }
}
