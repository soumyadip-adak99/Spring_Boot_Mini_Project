package net.soumya.JournalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class Users {

    @Id // this annotation make id filed make it primary key
    private ObjectId id;

    @Indexed(unique = true) // this annotation check the same username have in the collection or not
    @NonNull
    private String username;

    @NonNull // this annotation means this filed can't be null to passed
    private String password;

    @DBRef // this annotation using for make a reference of another collections
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
