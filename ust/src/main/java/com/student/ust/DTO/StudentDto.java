package com.student.ust.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class StudentDto {
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime Date,modifiedDate;

}
