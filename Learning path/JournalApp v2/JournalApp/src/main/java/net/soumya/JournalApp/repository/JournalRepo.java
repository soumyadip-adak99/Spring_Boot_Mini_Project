package net.soumya.JournalApp.repository;

import net.soumya.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalEntry, ObjectId> {
}
