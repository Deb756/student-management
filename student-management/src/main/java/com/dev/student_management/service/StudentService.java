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
    public StudentEntity addStud(StudentEntity newStud, String clgName) {
        AdminEntity college = adminRep.findByCollegeName(clgName);
        newStud.setCollege(college);
        newStud.setDate(LocalDateTime.now());
        StudentEntity student = studRep.save(newStud);
        college.getAllStudent().add(student);
        adminRep.save(college);
        return newStud;
    }

    //    deleting student
    public boolean removeById(ObjectId id, String collegeName) {
        AdminEntity clg = adminRep.findByCollegeName(collegeName);
        boolean b = clg.getAllStudent().removeIf(e -> e.getRegId().equals(id));
        if (b) {
            studRep.deleteById(id);
            adminRep.save(clg);
            return true;
        }
        return false;
    }

    //    update student
    public StudentEntity updateStudent(ObjectId id, StudentEntity newStud, String clgName) {
        AdminEntity college = adminRep.findByCollegeName(clgName);
        if (college == null) {
            throw new RuntimeException("College not found with name: " + clgName);
        }

        StudentEntity student = studRep.findById(id).orElse(null);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + id);
        }

        // Verify college ownership using DBRef only
        if (!student.getCollege().getId().equals(college.getId())) {
            throw new RuntimeException("Student does not belong to this college");
        }

        // Update only non-null fields
        if (newStud.getRegId() != null) student.setRegId(newStud.getRegId());
        if (newStud.getRollNo() != null) student.setRollNo(newStud.getRollNo());
        if (newStud.getName() != null) student.setName(newStud.getName());
        if (newStud.getEmail() != null) student.setEmail(newStud.getEmail());
        if (newStud.getAddress() != null) student.setAddress(newStud.getAddress());
        if (newStud.getCourse() != null) student.setCourse(newStud.getCourse());
        if (newStud.getSemester() != 0) student.setSemester(newStud.getSemester());
        if (newStud.getDate() != null) student.setDate(newStud.getDate());

        return studRep.save(student);
    }


}
