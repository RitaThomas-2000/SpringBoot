package com.student.ust.service;

import com.student.ust.DTO.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.InvalidPasswordException;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.UstUtill;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.student.ust.util.UstUtill.*;


/**
 * The type Student service.
 */
@Service
@Slf4j

public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * The Compile.
     */
    Pattern compile;
    /**
     * The Matcher.
     */
    Matcher matcher;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentByID(Integer id) throws NoSuchElementException {
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) {
        boolean validEmail= validEmail(student);
        boolean validPassword=validPassword(student);
        if(validEmail && validPassword) {
            student.setDate(LocalDateTime.now());
            student.setModifiedDate(student.getDate());
            String password = student.getPassword();
            student.setPassword(hashPassword(password));
            studentRepository.save(student);
        }
        else  if(!validEmail){
            throw new InvalidEmailException();
        }
        else{
            throw new InvalidPasswordException();
        }

    }




    /**
     * Gets all student.
     *
     * @return the all student
     */
    public List<Student> getAllStudent() {

        log.debug(studentRepository.findByAge(33).getName());
        log.debug(studentRepository.findByName("rita").getName());

        return studentRepository.findAll();
    }


    /**
     * Del student by id.
     *
     * @param id the id
     */
    public void delStudentByID(Integer id) {
        studentRepository.deleteById(id);
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student) {
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(() -> new NoSuchElementException());
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setModifiedDate(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;

    }


    public StudentDto converttoDto(Student student) {
        return modelMapper.map(student,StudentDto.class);

    }

    public List<StudentDto> converttoDto2(List<Student> studentList) {
        List<StudentDto> studentDtoList = modelMapper.map(studentList, new TypeToken<List<StudentDto>>() {}.getType());
        return studentDtoList;
    }

}
