package com.dev.student_management.controller;

import com.dev.student_management.entity.AdminEntity;
import com.dev.student_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//admin controlls
@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminServ;

    //    getting all Admins
    @GetMapping
    public List<AdminEntity> getAllAdmin() {
        return adminServ.getAll();
    }

    //    saving admin
    @PostMapping
    public AdminEntity saveAdmins(@RequestBody AdminEntity newAdmin) {
        return adminServ.saveAdmin(newAdmin);
    }

}

