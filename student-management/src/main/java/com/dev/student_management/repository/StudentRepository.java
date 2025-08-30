package com.dev.student_management.repository;

import com.dev.student_management.entity.StudentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, ObjectId> {
}
