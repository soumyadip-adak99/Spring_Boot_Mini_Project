package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepo journalRepo;

    // save
    public void saveEntry(JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalRepo.save(entry);
    }

    // get all
    public List<JournalEntry> getAllEntries() {
        return journalRepo.findAll();
    }

    // find by id
    public Optional<JournalEntry> getById(Object id) {
        return Optional.ofNullable(journalRepo.findById((ObjectId) id).orElse(null));
    }

    // delete method
    public void delteById(Object id) {
        journalRepo.deleteById((ObjectId) id);
    }

    //udpate entry by id
    public void updateEntryById(JournalEntry entry) {
        journalRepo.save(entry);
    }


}
