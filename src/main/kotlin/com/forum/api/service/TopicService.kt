package com.forum.api.service

import com.forum.api.controller.request.SaveTopicRequest
import com.forum.api.controller.request.UpdateTopicRequest
import com.forum.api.controller.response.TopicResponse
import com.forum.api.exception.NotFoundException
import com.forum.api.mapper.TopicMapper
import com.forum.api.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicMapper: TopicMapper,
    private val userService: UserService,
    private val courseService: CourseService
    ) {

    fun findAll(courseName: String?, pageable: Pageable): Page<TopicResponse> {
        val topics = if (courseName.isNullOrEmpty()) {
            topicRepository.findAll(pageable)
        } else {
            topicRepository.findByCourseName(courseName, pageable)
        }

        return topics.map { topic -> topicMapper.toTopicResponse(topic) }
    }

    fun findById(id: Long): TopicResponse {
        val topic = topicRepository.findById(id).orElseThrow{NotFoundException("Tópico não encontrado")}
        return topicMapper.toTopicResponse(topic)
    }

    fun save(topicRequest: SaveTopicRequest): TopicResponse {
        val user = userService.findById(topicRequest.userId)
        val course = courseService.findById(topicRequest.courseId)
        val topic = topicRepository.save(topicMapper.toTopic(topicRequest, user, course))

        return topicMapper.toTopicResponse(topic)
    }

    fun update(topicRequest: UpdateTopicRequest): TopicResponse {
        val topic =  topicRepository.findById(topicRequest.id)
            .orElseThrow{NotFoundException("Tópico não encontrado")}
        topic.title = topicRequest.title
        topic.message = topicRequest.message
        topicRepository.save(topic)

        return topicMapper.toTopicResponse(topic)
    }

    fun remove(id: Long) {
        topicRepository.deleteById(id)
    }
}