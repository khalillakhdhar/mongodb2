package com.mongod.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongod.documents.Course;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

}
