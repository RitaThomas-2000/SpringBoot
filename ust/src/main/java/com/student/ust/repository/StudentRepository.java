package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


     /**
      * Find by name student.
      *
      * @param name the name
      * @return the student
      */
     Student findByName(String name);

     /**
      * Find by age student.
      *
      * @param i the
      * @return the student
      */
     Student findByAge(int i);

     /**
      * Find by name starting with student.
      *
      * @param j the j
      * @return the student
      */
     Student findByNameStartingWith(String j);


     /**
      * Find student by age student.
      *
      * @param i the
      * @return the student
      */
     @Query(value = "Select s from Student s where s.age=?1")
     Student findStudentByAge(int i);
}


