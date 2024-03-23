package com.mongod.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongod.documents.Course;
import com.mongod.documents.Student;
import com.mongod.repos.CourseRepository;
import com.mongod.repos.StudentRepository;
import com.mongod.services.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Mono<Student> addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteStudentById(String id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public Mono<Student> enrollStudentToCourse(String studentId, String courseId) {
        // Récupérer l'étudiant par son ID
        Mono<Student> studentMono = studentRepository.findById(studentId);

        // Récupérer le cours par son ID
        Mono<Course> courseMono = courseRepository.findById(courseId);

        // Utiliser flatMap pour combiner les résultats et mettre à jour l'étudiant avec le cours
        return studentMono.zipWith(courseMono)
                .flatMap(tuple -> {
                    Student student = tuple.getT1();
                    Course course = tuple.getT2();
                    student.setCourse(course);
                    return studentRepository.save(student); 
                });
    }

}
