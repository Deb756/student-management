package com.dev.student_management.service;

import com.dev.student_management.entity.AdminEntity;
import com.dev.student_management.entity.StudentEntity;
import com.dev.student_management.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// admin service / logics
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    //    get all admin
    public List<AdminEntity> getAll() {
        return adminRepo.findAll();
    }

    //    save Admin
    public AdminEntity saveAdmin(AdminEntity newAdmin) {
        return adminRepo.save(newAdmin);
    }
}
