package com.soumya.JournalApp.repository;

import com.soumya.JournalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalEntry, String> {
}
