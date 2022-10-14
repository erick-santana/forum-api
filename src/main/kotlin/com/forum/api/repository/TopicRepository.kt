package com.forum.api.repository

import com.forum.api.controller.response.TopicReportResponse
import com.forum.api.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {

    fun findByCourseName(courseName: String, pageable: Pageable): Page<Topic>

    @Query("SELECT new com.forum.api.controller.response.TopicReportResponse(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicReportResponse>
}