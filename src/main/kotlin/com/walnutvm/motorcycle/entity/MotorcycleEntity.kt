package com.walnutvm.motorcycle.entity

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
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