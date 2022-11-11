package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


     Student findByName(String name);

     Student findByAge(int i);

     Student findByNameStartingWith(String j);



     @Query(value = "Select s from Student s where s.age=?1")
     Student findStudentByAge(int i);
}


