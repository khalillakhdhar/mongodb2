package com.mongod.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongod.documents.Course;
import com.mongod.repos.CourseRepository;
import com.mongod.services.CourseService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    
    private final CourseRepository courseRepository;

    @Override
    public Mono<Course> addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Flux<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Mono<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteCourseById(String id) {
        return courseRepository.deleteById(id);
    }
}
