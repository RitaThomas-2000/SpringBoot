package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id){
        log.debug("Passed in ID is >>>> "+id);
        try {
            Student student=studentService.getStudentByID(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/students")
    public void add(@RequestBody Student student){
        log.debug("Passed in Student details >>>>"+ student.getName()+" "+student.getAge()+" "+student.getRollNo());
        studentService.saveStudent(student);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> get()
    {
        try
        {
            List<Student> studentList = studentService.getAllStudent();
            return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id ){
        studentService.delStudentByID(id);
    }

    @PutMapping("/student")
    public ResponseEntity<Student> put(@RequestBody Student student){
        try {
            Student updatedstudent =studentService.updateStudent(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }




}
