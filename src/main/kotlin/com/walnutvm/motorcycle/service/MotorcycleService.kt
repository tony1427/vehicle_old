package com.walnutvm.motorcycle.service

import com.github.dozermapper.core.Mapper
import com.walnutvm.motorcycle.exception.NotFoundException
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.repository.MotorcycleRepository
import com.walnutvm.motorcycle.utils.toUUID
import org.springframework.stereotype.Service

@Service
class MotorcycleService (
    private val motorcycleRepository: MotorcycleRepository,
    private val dozerMapper: Mapper
        ) {

    fun getMotorcycle(id: String): MotorcycleRepresentation {
        return motorcycleRepository.findById(id.toUUID())
            .map { dozerMapper.map(it, MotorcycleRepresentation::class.java) }
            .orElseThrow{ NotFoundException() }
    }
    fun createMotorcycle(motorcycleRepresentation: MotorcycleRepresentation): MotorcycleRepresentation {
        return motorcycleRepository.save(motorcycleRepresentation.toEntity()).toRepresentation()
    }

    fun updateMotorcycle(id: String, motorcycleRepresentation: MotorcycleRepresentation): MotorcycleRepresentation {
        val motorcycle = motorcycleRepository.findById(id.toUUID()).orElseThrow { NotFoundException() }

        motorcycle.apply {
            vin = motorcycleRepresentation.vin
            make = motorcycleRepresentation.make
            model = motorcycleRepresentation.model
            purchaseDate = motorcycleRepresentation.purchaseDate
        }

        return motorcycleRepository.save(motorcycle).toRepresentation()
    }


}