package com.walnutvm.motorcycle.service

import org.springframework.stereotype.Component

@Component
class MotorcycleServiceHelper {
    fun validateRequest(requestMap: Map<String, String>, classFieldNames: List<String>): List<String> {

        val keys = requestMap.keys.toList()

        return keys.filterNot { classFieldNames.contains(it) }

//        return if (difference.isEmpty()) {
//            Pair(true, listOf())
//        } else {
//            Pair(false, difference)
//        }
    }
}