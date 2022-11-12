package com.walnutvm.motorcycle.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomNumeric
import java.text.SimpleDateFormat
import java.time.LocalDate


fun <T> asJsonString(obj: T): String{
    try {
        val df = SimpleDateFormat("yyyy-MM-dd")

        return ObjectMapper().registerModule(JavaTimeModule()).writeValueAsString(obj)
    } catch (e: Exception){
        throw RuntimeException(e)
    }
}


fun createRandomMotorcycle() = MotorcycleRepresentation(
    randomNumeric(17),
    randomAlphabetic(10),
    randomAlphabetic(20),
    LocalDate.now()
)

fun createRandonVehicleMap(): Map<String,String>{
    return mapOf(
        "vin" to randomNumeric(17),
        "make" to randomAlphabetic(10),
        "model" to randomAlphabetic(10),
        "purchaseDate" to LocalDate.now().toString()
    )
}