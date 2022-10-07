package com.forum.api.mapper

import com.forum.api.controller.request.SaveTopicRequest
import com.forum.api.controller.response.TopicResponse
import com.forum.api.model.Course
import com.forum.api.model.Topic
import com.forum.api.model.User
import org.springframework.stereotype.Component

@Component
class TopicMapper {

    fun toTopicResponse(topic: Topic): TopicResponse {
        return TopicResponse(
            id = topic.id,
            title = topic.title,
            message = topic.message,
            status = topic.status,
            createdAt = topic.createdAt
        )
    }

    fun toTopic(topic: SaveTopicRequest, user: User, course: Course): Topic {
        return Topic(
            title = topic.title,
            message = topic.message,
            author = user,
            course = course
        )
    }
}