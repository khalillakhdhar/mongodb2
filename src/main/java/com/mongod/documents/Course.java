package com.mongod.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Document
@Data
public class Course {

    @Id
    private String id;

    @NotEmpty(message = "Le nom du cours ne doit pas être vide")
    @Size(min = 3,max = 30)
    private String name;

    @JsonIgnoreProperties("course")
    private List<Student> students;

}