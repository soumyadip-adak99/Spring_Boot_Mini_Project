package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UsersServices usersServices;

    // save
    public void saveEntry(JournalEntry entry, String username) {
        Users user = usersServices.getByUserName(username).orElse(null);
        entry.setDate(LocalDateTime.now());
        assert user != null;
        user.getJournalEntries().add(journalRepository.save(entry));
        usersServices.addUser(user);
    }

    // this overloaded method use for update journal entries
    public void saveEntry(JournalEntry entry) {
        journalRepository.save(entry);
    }

    // get all
    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    // find by id
    public Optional<JournalEntry> getById(Object id) {
        return Optional.ofNullable(journalRepository.findById((ObjectId) id).orElse(null));
    }

    // delete method
    public void deleteById(ObjectId id, String username) {
        Users user = usersServices.getByUserName(username).orElse(null);
        assert user != null;
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        usersServices.addUser(user);
        journalRepository.deleteById(id);

    }
}
