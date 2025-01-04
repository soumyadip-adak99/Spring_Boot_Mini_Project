package net.soumya.JournalApp.repository;

import net.soumya.JournalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUsername(String username);
}
