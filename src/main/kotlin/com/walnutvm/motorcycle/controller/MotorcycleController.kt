package com.walnutvm.motorcycle.controller

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.service.MotorcycleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("motorcycles")
class MotorcycleController (
    private val motorcycleService: MotorcycleService
        ){

    @GetMapping
    fun getMotocycles(): String{
        return "Hello"
    }

    @PostMapping
    fun addMotorcycles(@RequestBody motorcycleRepresentation: MotorcycleRepresentation):ResponseEntity<Unit> {
        println(motorcycleRepresentation)
        val motorcycle = motorcycleService.createMotorcycle(motorcycleRepresentation)
        return entityCreatedResponse(motorcycle.id!!)
    }

    @PatchMapping
    fun updateMotocycles(){
        //todo implement
    }

    @DeleteMapping
    fun deleteMotorcycles(){
        //todo implement
    }
}