package com.walnutvm.motorcycle.service

import com.walnutvm.motorcycle.exception.BadActionException
import com.walnutvm.motorcycle.exception.NotFoundException
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.repository.MotorcycleRepository
import com.walnutvm.motorcycle.utils.toUUID
import org.apache.commons.beanutils.BeanUtilsBean
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.BadRequest

@Service
class MotorcycleService (
    private val motorcycleRepository: MotorcycleRepository,
    private val modelMapper: ModelMapper,
    private val beanUtilsBean: BeanUtilsBean
        ) {

    fun getMotorcycle(id: String): MotorcycleRepresentation {
        return motorcycleRepository.findById(id.toUUID())
            .map { modelMapper.map(it, MotorcycleRepresentation::class.java) }
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

    fun updateMotorcycle(id: String, map: Map<String, String>){
        val motorcycle = motorcycleRepository.findById(id.toUUID()).orElseThrow { NotFoundException() }

        if(map.isEmpty() || isFieldUnknown(map)){
            throw BadActionException("Field(s) doesn't exist ${map.keys}")
        }

        print(motorcycle)
    }

    private fun isFieldUnknown(map: Map<String, String>): Boolean {

//        for ((key, value) in map){
//            when(key){
//
//            }
//        }
        return true;
    }
}