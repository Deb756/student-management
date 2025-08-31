package com.dev.student_management.controller;

import com.dev.student_management.entity.StudentEntity;
import com.dev.student_management.service.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    //    all api end points and its http status code
    @Autowired
    StudentService studServ;

    //    save student
    @PostMapping
    public ResponseEntity<StudentEntity> saveStudent(@RequestBody StudentEntity newStud, @RequestParam("clgName") String clgName) {
        try {
            return new ResponseEntity<>(studServ.addStud(newStud, clgName), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    //    get All saved student
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudent() {
        List<StudentEntity> all = studServ.getAll();
        if (all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    get student by id
    @GetMapping("/byId")
    public ResponseEntity<StudentEntity> getStudentById(@RequestParam("id") ObjectId id) {
        StudentEntity stud = studServ.getById(id);
        if (stud != null)
            return new ResponseEntity<>(stud, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    update student info
    @PutMapping("/byId")
    public ResponseEntity<StudentEntity> updateStudentInfo(@RequestParam("id") ObjectId id, @RequestBody StudentEntity newStud, @RequestParam("clgName") String clgName) {
        StudentEntity stud = studServ.updateStudent(id, newStud, clgName);
        if (stud != null)
            return new ResponseEntity<>(stud, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    delete student by id
    @DeleteMapping("/byId")
    public ResponseEntity<?> deleteStudentById(@RequestParam("id") ObjectId id, @RequestParam("clgName") String clgName) {
        if (studServ.removeById(id, clgName))
            return new ResponseEntity<>(true, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
