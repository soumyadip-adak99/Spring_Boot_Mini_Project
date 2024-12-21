package com.soumya.SpringMangoProjectDB.repo;

import com.soumya.SpringMangoProjectDB.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, String> {
}
