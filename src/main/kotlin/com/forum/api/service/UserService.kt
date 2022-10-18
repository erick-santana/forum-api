package com.forum.api.service

import com.forum.api.exception.NotFoundException
import com.forum.api.model.User
import com.forum.api.model.UserDetail
import com.forum.api.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
    ): UserDetailsService {

    fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow{NotFoundException("Usuário não encontrado!")}
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw NotFoundException("Usuário não encontrado")

        return UserDetail(user)
    }
}