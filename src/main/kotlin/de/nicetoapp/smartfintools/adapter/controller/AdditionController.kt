package de.nicetoapp.smartfintools.adapter.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AdditionController {

    @GetMapping("/add")
    fun add(
        @RequestParam("number1") number1: Double, 
        @RequestParam("number2") number2: Double
    ): Map<String, Any> {
        val sum = number1 + number2
        return mapOf("number1" to number1, "number2" to number2, "result" to sum)
    }

    @GetMapping("/subtract")
    fun subtract(
        @RequestParam("number1") number1: Double,
        @RequestParam("number2") number2: Double
    ): Map<String, Any> {
        val sum = number1 - number2
        return mapOf("number1" to number1, "number2" to number2, "result" to sum)
    }
}
