package net.soumya.JournalApp.repository;


import net.soumya.JournalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
    Users findByUsername(String username);
}
