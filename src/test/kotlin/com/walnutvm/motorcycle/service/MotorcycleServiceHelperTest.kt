package com.walnutvm.motorcycle.service

import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import java.util.stream.Collectors
import kotlin.reflect.full.declaredMemberProperties

internal class MotorcycleServiceHelperTest {

    private val fieldNames: MutableList<String> =
        MotorcycleRepresentation::class.declaredMemberProperties.stream().map { f -> f.name }.collect(
            Collectors.toList()
        )


    private var motorcycleServiceHelper: MotorcycleServiceHelper = MotorcycleServiceHelper()


    @Test
    fun `validate full request should return zero unknown fields`() {
        val requestMap = mapOf(
            "make" to "make",
            "model" to "model",
            "vin" to "vin",
            "purchaseDate" to "2022-10-29"
        )

        val response = motorcycleServiceHelper.validateRequest(requestMap, fieldNames)

        assertThat(response).isEmpty()
    }

    @Test
    fun `validate partial request should return zero unknown fields`() {
        val requestMap = mapOf(
            "make" to "make",
        )

        val response = motorcycleServiceHelper.validateRequest(requestMap, fieldNames)

        assertThat(response).isEmpty()
    }

    @Test
    fun `validate partial request with unknown field should return unknown fields`() {
        val requestMap = mapOf(
            "make" to "make",
            "lake" to "lake"
        )

        val response = motorcycleServiceHelper.validateRequest(requestMap, fieldNames)

        assertThat(response).isNotEmpty
        assertThat(response).containsExactlyInAnyOrderElementsOf(listOf("lake"))
    }

    @Test
    fun `validate partial request with multiple unknown fields should return unknown fields`() {
        val requestMap = mapOf(
            "make" to "make",
            "lake" to "lake",
            "ocean" to "ocean"
        )

        val response = motorcycleServiceHelper.validateRequest(requestMap, fieldNames)

        assertThat(response).isNotEmpty
        assertThat(response).containsExactlyInAnyOrderElementsOf(listOf("lake", "ocean"))
    }


    @Test
    fun `validate full request with an unknown field should return unknown fields`() {
        val requestMap = mapOf(
            "make" to "make",
            "model" to "model",
            "vin" to "vin",
            "purchaseDate" to "2022-10-29",
            "extraField" to "extraField"
        )

        val response = motorcycleServiceHelper.validateRequest(requestMap, fieldNames)

        assertThat(response).isNotEmpty
        assertThat(response).containsExactlyInAnyOrderElementsOf(listOf("extraField"))
    }
}