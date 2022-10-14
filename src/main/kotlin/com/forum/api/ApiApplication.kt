package com.forum.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
open class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}
