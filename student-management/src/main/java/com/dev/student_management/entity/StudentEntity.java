package com.dev.student_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "student-data")
public class StudentEntity {

    @Id
    private ObjectId regId;
    @NonNull
    private Long rollNo;
    @NonNull
    private String name;
    @NonNull
    @Indexed(unique = true)
    private String email;
    private String address;
    @NonNull
    private String course;
    @NonNull
    private int semester;
    @NonNull
    private LocalDateTime date;

    @DBRef
    @JsonBackReference
    private AdminEntity college;

    public ObjectId getRegId() {
        return regId;
    }

    public void setRegId(ObjectId regId) {
        this.regId = regId;
    }

    @NonNull
    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(@NonNull Long rollNo) {
        this.rollNo = rollNo;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    public String getCourse() {
        return course;
    }

    public void setCourse(@NonNull String course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @NonNull
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(@NonNull LocalDateTime date) {
        this.date = date;
    }

    public AdminEntity getCollege() {
        return college;
    }

    public void setCollege(AdminEntity college) {
        this.college = college;
    }
}
