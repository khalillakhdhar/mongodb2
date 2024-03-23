package com.mongod.services;
import com.mongod.documents.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Mono<Student> addStudent(Student student);
    Flux<Student> getAllStudents();
    Mono<Student> getStudentById(String id);
    Mono<Void> deleteStudentById(String id);
    Mono<Student> enrollStudentToCourse(String studentId, String courseId);
}
