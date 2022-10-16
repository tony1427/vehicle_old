package com.walnutvm.motorcycle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MotorcycleApplication

fun main(args: Array<String>) {
	runApplication<MotorcycleApplication>(*args)
}
