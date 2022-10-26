package com.walnutvm.motorcycle.entity

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "motorcycle")
class MotorcycleEntity(


    @Column(name = "vin")
    var vin: String,

    var make: String,

    var model: String,

    @Column(name = "purchase_date")
    var purchaseDate: LocalDate,

): UpdatableEntity(){
    fun toRepresentation(): MotorcycleRepresentation = let {
        MotorcycleRepresentation(
            vin = vin,
            make = make,
            model = model ,
            purchaseDate
        )
            .apply { id = it.id.toString() }
    }
}