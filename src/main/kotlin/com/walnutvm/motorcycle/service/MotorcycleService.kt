package com.walnutvm.motorcycle.service

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.repository.MotorcycleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MotorcycleService (
    private val motorcycleRepository: MotorcycleRepository
        ) {

    fun createMotorcycle(motorcycleRepresentation: MotorcycleRepresentation): MotorcycleRepresentation {
        return motorcycleRepository.save(motorcycleRepresentation.toEntity()).toRepresentation()
    }
}