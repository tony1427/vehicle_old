package com.walnutvm.motorcycle.service

import com.walnutvm.motorcycle.exception.BadActionException
import com.walnutvm.motorcycle.exception.NotFoundException
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.repository.MotorcycleRepository
import com.walnutvm.motorcycle.utils.ApplicationFactory
import com.walnutvm.motorcycle.utils.toUUID
import org.apache.commons.beanutils.BeanUtilsBean
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.reflect.full.declaredMemberProperties

@Service
class MotorcycleService(
    private val motorcycleRepository: MotorcycleRepository,
    private val modelMapper: ModelMapper,
    private val beanUtilsBean: BeanUtilsBean,
    private val applicationFactory: ApplicationFactory,
    private val motorcycleServiceHelper: MotorcycleServiceHelper
) {

    fun getMotorcycle(id: String): MotorcycleRepresentation {
        return motorcycleRepository.findById(id.toUUID())
            .map { modelMapper.map(it, MotorcycleRepresentation::class.java) }
            .orElseThrow { NotFoundException() }
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

    fun updateMotorcycle(id: String, map: Map<String, String>) {

        if (map.isEmpty()) {
            throw BadActionException("Map request is empty")
        }

        val motorcycle = motorcycleRepository.findById(id.toUUID()).orElseThrow { NotFoundException() }

        val properties = applicationFactory.getProperties<MotorcycleRepresentation>()
        val validateMap = motorcycleServiceHelper.validateMap(map, properties)

        if (validateMap.first) {
            throw BadActionException("Fields don't exist ${validateMap.second}")
        }

        //populate the opp entity and save

        print(motorcycle)
    }


}


fun main() {
    val map = mapOf(
        "1" to "This",
        "2" to "That",
        "3" to "the other"
    )

    println("Keys ${map.keys}")

    val props = MotorcycleRepresentation::class.declaredMemberProperties.stream().map { f -> f.name }
        .collect(Collectors.toList())

    println("prop: $props")
    val list = listOf("make1", "model2")

    val difference = props.filterNot { list.contains(it) }
//    https://www.techiedelight.com/difference-between-two-lists-kotlin/
    val difference2 = list.filterNot { props.contains(it) }// <- use this

    println("Difference: $difference")
    println("Difference: $difference2")
}
