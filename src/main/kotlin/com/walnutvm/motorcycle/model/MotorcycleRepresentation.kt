package com.walnutvm.motorcycle.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.walnutvm.motorcycle.config.NoArgConstructor
import com.walnutvm.motorcycle.entity.MotorcycleEntity
import java.time.LocalDate


@NoArgConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MotorcycleRepresentation(
    var id: String? = null,
    var vin: String,
    var make: String,
    var model: String,
    var purchaseDate: LocalDate

) {
    fun toEntity(): MotorcycleEntity = MotorcycleEntity(vin = vin, make = make, model = model, purchaseDate = purchaseDate)
}