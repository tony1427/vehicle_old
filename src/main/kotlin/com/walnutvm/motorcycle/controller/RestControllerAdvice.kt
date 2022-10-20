package com.walnutvm.motorcycle.controller

import com.walnutvm.motorcycle.config.Logging
import com.walnutvm.motorcycle.exception.NotFoundException
import com.walnutvm.motorcycle.utils.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

@RestControllerAdvice
class RestControllerAdvice: Logging {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: RuntimeException){
        logger().error("${e.javaClass.name}: ${e.message ?: "No message"}")
    }
}