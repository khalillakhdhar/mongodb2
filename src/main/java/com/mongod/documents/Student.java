package com.mongod.documents;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Document
@Data
public class Student {

    @Id
    private String id;

    @NotEmpty(message = "Le nom de l'étudiant ne doit pas être vide")
    private String name;

    @Positive(message = "L'âge de l'étudiant doit être un entier positif")
    private int age;
    @DBRef
    @JsonIgnoreProperties("students")

    private Course course; // Référence vers la classe Course


}
