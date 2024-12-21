package com.soumya.SpringMangoProjectDB.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
}
