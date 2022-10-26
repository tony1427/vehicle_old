package com.walnutvm.motorcycle.controller

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.service.MotorcycleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("motorcycles")
class MotorcycleController(
    private val motorcycleService: MotorcycleService
) {

    @GetMapping("/{id}")
    fun getMotocycles(@PathVariable id: String): MotorcycleRepresentation {
        return motorcycleService.getMotorcycle(id)
    }

    @PostMapping
    fun createMotorcycles(@RequestBody motorcycleRepresentation: MotorcycleRepresentation): ResponseEntity<Unit> {
        println(motorcycleRepresentation)
        val motorcycle = motorcycleService.createMotorcycle(motorcycleRepresentation)
        return entityCreatedResponse(motorcycle.id!!)
    }

    @PutMapping("/{id}")
    fun updateMotocycles(
        @PathVariable id: String,
        @RequestBody motorcycleRepresentation: MotorcycleRepresentation
    ): ResponseEntity<Unit> {
        println(motorcycleRepresentation)

        motorcycleService.updateMotorcycle(id, motorcycleRepresentation)
        return entityNoContentResponse()
    }

    @PatchMapping("/id")
    fun updateMotocycles() {
        //todo implement
    }

    @DeleteMapping("/id")
    fun deleteMotorcycles() {
        //todo implement
    }
}