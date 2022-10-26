package com.walnutvm.motorcycle.controller

import com.ninjasquad.springmockk.MockkBean
import com.walnutvm.motorcycle.controller.ApplicationConstants.MOTORCYCLES
import com.walnutvm.motorcycle.exception.NotFoundException
import com.walnutvm.motorcycle.model.MotorcycleRepresentation
import com.walnutvm.motorcycle.service.MotorcycleService
import com.walnutvm.motorcycle.util.asJsonString
import com.walnutvm.motorcycle.util.createRandomMotorcycle
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
@WebMvcTest(MotorcycleController::class)
internal class MotorcycleControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var motorcycleService: MotorcycleService

    @Test
    fun `get motorcycle by id`() {
        val motorcycle = createRandomMotorcycle()
        every { motorcycleService.getMotorcycle("someId") } returns motorcycle

        mockMvc.perform(MockMvcRequestBuilders.get("/$MOTORCYCLES/someId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.vin").value(motorcycle.vin))
            .andExpect(jsonPath("$.make").value(motorcycle.make))
            .andExpect(jsonPath("$.model").value(motorcycle.model))
            .andExpect(jsonPath("$.purchaseDate").value(motorcycle.purchaseDate.toString()))
            .andReturn()
    }

    @Test
    fun `get motorcycle by id returns not found exception`() {
        every { motorcycleService.getMotorcycle("someId") } throws (NotFoundException())

        mockMvc.perform(MockMvcRequestBuilders.get("/$MOTORCYCLES/someId"))
            .andExpect(status().isNotFound)
            .andReturn()
    }

    @Test
    fun `create motorcycle should return created status`() {
        val motorcycle = createRandomMotorcycle()
        every { motorcycleService.createMotorcycle(motorcycle) }
            .returns(MotorcycleRepresentation("vin", "make", "model", LocalDate.now()).apply { id = "someId" })

        val result: MockHttpServletResponse =
            mockMvc.perform(
                MockMvcRequestBuilders.post("/$MOTORCYCLES")
                    .content(asJsonString(motorcycle))
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isCreated)
                .andReturn()
                .response

        assertThat(result.getHeaders("Location")).contains("http://localhost/$MOTORCYCLES/someId")
    }

    @Test
    fun `update an existing motorcycle`() {
        val motorcycle = createRandomMotorcycle()

        every { motorcycleService.updateMotorcycle("someId", motorcycle) }
            .returns(MotorcycleRepresentation("vin", "make", "model", LocalDate.now()).apply { id = "someId" })

        mockMvc.perform(
            MockMvcRequestBuilders.put("/$MOTORCYCLES/someId")
                .content(asJsonString(motorcycle)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }

    @Test
    fun `update an existing motorcycle returns not found `() {
        val motorcycle = createRandomMotorcycle()

        every { motorcycleService.updateMotorcycle("someId", motorcycle) }.throws(NotFoundException())

        mockMvc.perform(
            MockMvcRequestBuilders.put("/$MOTORCYCLES/someId")
                .content(asJsonString(motorcycle)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound)

            .andReturn()
    }

    @Test
    fun testUpdateMotocycles() {
    }

    @Test
    fun deleteMotorcycles() {
    }
}