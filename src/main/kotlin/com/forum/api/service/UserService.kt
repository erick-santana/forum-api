package com.forum.api.service

import com.forum.api.exception.NotFoundException
import com.forum.api.model.User
import com.forum.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow{NotFoundException("Usuário não encontrado!")}
    }
}