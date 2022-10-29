package com.walnutvm.motorcycle.service

import org.springframework.stereotype.Component

@Component
class MotorcycleServiceHelper {
    fun validateMap(map: Map<String, String>, properties: List<String>): Pair<Boolean, List<String>> {

        val keys = map.keys.toList()

        val difference = keys.filterNot { properties.contains(it) }

        return if (difference.isEmpty()) {
            Pair(true, listOf())
        } else {
            Pair(false, difference)
        }
    }
}