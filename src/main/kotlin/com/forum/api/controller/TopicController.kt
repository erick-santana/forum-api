package com.forum.api.controller

import com.forum.api.controller.request.SaveTopicRequest
import com.forum.api.controller.request.UpdateTopicRequest
import com.forum.api.controller.response.TopicReportResponse
import com.forum.api.controller.response.TopicResponse
import com.forum.api.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("topic")
open class TopicController(private val service: TopicService) {

    @GetMapping("/all")
    @Cacheable("topics")
    fun findAll(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5) pageable: Pageable
    ): Page<TopicResponse> {
        return service.findAll(courseName, pageable)
    }

    @GetMapping("/{id}")
    fun findAll(@PathVariable id: Long): TopicResponse {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun save(
        @RequestBody @Valid topicRequest: SaveTopicRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicResponse> {
        val topic = service.save(topicRequest)
        val uri = uriBuilder.path("/topic/${topic.id}").build().toUri();
        return ResponseEntity.created(uri).body(topic)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@RequestBody @Valid topic: UpdateTopicRequest): TopicResponse {
        return service.update(topic)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun remove(@PathVariable id: Long) {
        return service.remove(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicReportResponse> {
        return service.report()
    }
}