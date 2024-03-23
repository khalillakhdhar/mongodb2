package com.mongod.services;

import com.mongod.documents.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Mono<Course> addCourse(Course course);
    Flux<Course> getAllCourses();
    Mono<Course> getCourseById(String id);
    Mono<Void> deleteCourseById(String id);
}
