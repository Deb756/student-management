package com.dev.student_management.service;

import com.dev.student_management.entity.AdminEntity;
import com.dev.student_management.entity.StudentEntity;
import com.dev.student_management.repository.AdminRepository;
import com.dev.student_management.repository.StudentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {
    //    all the logics
    @Autowired
    StudentRepository studRep;

    @Autowired
    AdminRepository adminRep;
    //    fetching all student
    public List<StudentEntity> getAll() {
        return studRep.findAll();
    }

    //    fetching student by id
    public StudentEntity getById(ObjectId id) {
        return studRep.findById(id).orElse(null);
    }

    //    adding student
    public StudentEntity addStud(StudentEntity newStud,String clgName) {
        AdminEntity college = adminRep.findByCollegeName(clgName);
        newStud.setCollege(college);
        newStud.setDate(LocalDateTime.now());
        StudentEntity student = studRep.save(newStud);
        college.getAllStudent().add(student);
        adminRep.save(college);
        return newStud;
    }
//    deleting student
    public boolean removeById(ObjectId id)
    {
        if(getById(id) != null)
        {
            studRep.deleteById(id);
            return true;
        }
        return false;
    }

    //    update student
    public StudentEntity updateStudent(ObjectId id, StudentEntity newStud) {
        return studRep.findById(id).map(existing -> {

            if (newStud.getRegId() != null) existing.setRegId(newStud.getRegId());
            if (newStud.getRollNo() != null) existing.setRollNo(newStud.getRollNo());
            if (newStud.getName() != null) existing.setName(newStud.getName());
            if (newStud.getEmail() != null) existing.setEmail(newStud.getEmail());
            if (newStud.getAddress() != null) existing.setAddress(newStud.getAddress());
            if (newStud.getCourse() != null) existing.setCourse(newStud.getCourse());
            if (newStud.getSemester() != 0) existing.setSemester(newStud.getSemester());
            if (newStud.getDate() != null) existing.setDate(newStud.getDate());

            return studRep.save(existing);

        }).orElse(null);
    }
}
