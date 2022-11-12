package com.walnutvm.motorcycle.controller

import com.github.fge.jsonpatch.JsonPatch
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.service.MotorcycleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.DeleteMapping




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

    @PatchMapping("/{id}")
    fun updateMotocycles(@PathVariable id: String,@RequestBody map: Map<String, String>): ResponseEntity<Unit> {
        motorcycleService.updateMotorcycle(id, map)

        return entityNoContentResponse()
    }

//    @PatchMapping("/{id}", consumes = ["application/json-patch+json"])
//    fun updateMotocycles(@PathVariable id: String, @RequestBody patch: JsonPatch): ResponseEntity<Unit> {
//        println(patch)
//        motorcycleService.patchVehicle(id, patch)
//
//        return entityNoContentResponse()
//    }

    @DeleteMapping("/{id}")
    fun deleteMotorcycles() {
        //todo implement
    }
}