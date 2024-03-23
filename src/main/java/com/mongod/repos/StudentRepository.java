package com.mongod.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongod.documents.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

}
