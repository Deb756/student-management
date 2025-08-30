package com.dev.student_management.repository;

import com.dev.student_management.entity.AdminEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// admin repo
@Repository
public interface AdminRepository extends MongoRepository<AdminEntity, String> {
    public AdminEntity findByCollegeName(String collegeName);
}
