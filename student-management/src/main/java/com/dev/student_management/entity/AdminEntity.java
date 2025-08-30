package com.dev.student_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

// admin info
@Document(collection = "college-db")
public class AdminEntity {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String collegeName;
    @NonNull
    private String location;

    @DBRef(lazy = true)
    @JsonManagedReference
    List<StudentEntity> allStudent = new ArrayList<>();

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<StudentEntity> getAllStudent() {
        return allStudent;
    }

    public void setAllStudent(List<StudentEntity> allStudent) {
        this.allStudent = allStudent;
    }
}
