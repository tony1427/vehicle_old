package com.walnutvm.motorcycle.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.walnutvm.motorcycle.config.NoArgConstructor
import com.walnutvm.motorcycle.entity.MotorcycleEntity
import java.time.LocalDate
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@NoArgConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MotorcycleRepresentation(
    @field:Size(max =17)
    var vin: String,
    @field:NotNull
    var make: String,
    @field:NotNull
    var model: String,
    @field:NotNull
    var purchaseDate: LocalDate

) : BaseRepresentation() {
    fun toEntity(): MotorcycleEntity = MotorcycleEntity(vin, make, model, purchaseDate)
}