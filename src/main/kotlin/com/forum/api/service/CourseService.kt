package com.forum.api.service

import com.forum.api.exception.NotFoundException
import com.forum.api.model.Course
import com.forum.api.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun findById(id: Long): Course {
        return courseRepository.findById(id).orElseThrow{ NotFoundException("Curso não encontrado!")}
    }
}