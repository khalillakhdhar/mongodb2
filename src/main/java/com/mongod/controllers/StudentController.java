package com.mongod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mongod.documents.Student;
import com.mongod.exceptions.CourseNotFoundException;
import com.mongod.exceptions.InvalidDataException;
import com.mongod.exceptions.StudentNotFoundException;
import com.mongod.services.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Mono<ResponseEntity<Student>> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student)
                .map(savedStudent -> ResponseEntity.status(HttpStatus.CREATED).body(savedStudent))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping
    public Flux<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id)
                .map(student -> ResponseEntity.ok().body(student))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteStudentById(@PathVariable String id) {
        return studentService.deleteStudentById(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{studentId}/enroll/{courseId}")
    public Mono<ResponseEntity<Student>> enrollStudentToCourse(@PathVariable String studentId, @PathVariable String courseId) {
        return studentService.enrollStudentToCourse(studentId, courseId) // instance de Mono<Student>
                .map(updatedStudent -> ResponseEntity.ok().body(updatedStudent)) // sénario attendu
                .onErrorResume(error -> {
                    if (error instanceof StudentNotFoundException || error instanceof CourseNotFoundException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                    } else if (error instanceof InvalidDataException) {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                    }
                });
    }
}
