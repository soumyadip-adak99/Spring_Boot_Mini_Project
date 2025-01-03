package net.soumya.JournalApp.services;

import net.soumya.JournalApp.entity.JournalEntry;
import net.soumya.JournalApp.entity.Users;
import net.soumya.JournalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void saveEntry(JournalEntry entry, String username) {
        Users user = usersServices.getByUserName(username);
        entry.setDate(LocalDateTime.now());
        JournalEntry entries = journalRepository.save(entry);
        user.getJournalEntries().add(entries);
//        user.setUsername(null);
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
        Users user = usersServices.getByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        usersServices.addUser(user);
        journalRepository.deleteById(id);
    }
}
