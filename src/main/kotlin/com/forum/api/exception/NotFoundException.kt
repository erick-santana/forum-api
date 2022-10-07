package com.forum.api.exception

class NotFoundException(override val message: String?): RuntimeException(message) {

}