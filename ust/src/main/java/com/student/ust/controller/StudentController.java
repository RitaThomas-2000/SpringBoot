package com.student.ust.controller;

import com.student.ust.DTO.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.BusinessException;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.repository.StudentRepository;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Long.toHexString;

/**
 * The type Student controller.
 */
@RestController
@Slf4j
public class StudentController {
    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable Integer id){
        log.debug("Passed in ID is >>>> "+id);
        try {
            Student student=studentService.getStudentByID(id);
            return new ResponseEntity<StudentDto>(studentService.converttoDto(student), HttpStatus.OK);

        }catch(NoSuchElementException e){
            return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/students")
    public ResponseEntity<Student> add(@RequestBody Student student) throws BusinessException{
        try{

        studentService.saveStudent(student);
        log.debug("Passed in student details >>>"+student.getName()+" "+student.getAge());
        return new ResponseEntity<Student>(student,HttpStatus.OK);
        }
        catch (BusinessException e){
            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
    }



    /**
     * Get response entity.
     *
     * @return the response entity
     */
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> get()
    {
        try
        {
            List<Student> studentList = studentService.getAllStudent();
            return new ResponseEntity<List<StudentDto>>(studentService.converttoDto2(studentList), HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<List<StudentDto>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable Integer id ){
        studentService.delStudentByID(id);
    }

    /**
     * Put response entity.
     *
     * @param student the student
     * @return the response entity
     */
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
