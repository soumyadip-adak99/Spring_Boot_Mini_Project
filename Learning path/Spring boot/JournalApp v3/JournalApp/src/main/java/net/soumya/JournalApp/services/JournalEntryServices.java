package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UsersServices usersServices;

    // save
    public void saveEntry(JournalEntry entry, String username) {
        Users user = usersServices.getByUserName(username).orElse(null);
        entry.setDate(LocalDateTime.now());
        assert user != null;
        user.getJournalEntries().add(journalRepo.save(entry));
        usersServices.addUser(user);
    }

    // this overloaded method use for update journal entries
    public void saveEntry(JournalEntry entry) {
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
    public void deleteById(Object id, String username) {
        Users user = usersServices.getByUserName(username).orElse(null);
        assert user != null;
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        usersServices.addUser(user);
        journalRepo.deleteById((ObjectId) id);

    }
}
