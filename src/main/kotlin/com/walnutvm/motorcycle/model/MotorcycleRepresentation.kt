package com.walnutvm.motorcycle.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.walnutvm.motorcycle.config.NoArgConstructor
import com.walnutvm.motorcycle.entity.MotorcycleEntity
import java.time.LocalDate


@NoArgConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MotorcycleRepresentation(
    var vin: String? = null,
    var make: String? = null,
    var model: String? = null,
    var purchaseDate: LocalDate? = null

) : BaseRepresentation() {
    fun toEntity(): MotorcycleEntity = MotorcycleEntity(vin = vin!!, make = make!!, model = model!!, purchaseDate = purchaseDate!!)
}