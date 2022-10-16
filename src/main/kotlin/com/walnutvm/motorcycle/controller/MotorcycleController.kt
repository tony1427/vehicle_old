package com.walnutvm.motorcycle.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("motorcycles")
class MotorcycleController {

    @GetMapping
    fun getMotocycles(): String{
        return "Hello"
    }

    @PostMapping
    fun addMotorcycles() {
        //todo implement
    }

    @PatchMapping
    fun updateMotocycles(){
        //todo implement
    }

    @DeleteMapping
    fun deleteMotorcycles(){
        //todo implement
    }
}