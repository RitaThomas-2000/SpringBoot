package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="book_ust_details_mappedBy")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String authorName;
    private long isbn;
    private LocalDateTime Date,modifiedDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;


}
