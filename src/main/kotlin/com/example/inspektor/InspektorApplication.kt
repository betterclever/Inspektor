package com.example.inspektor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InspektorApplication

fun main(args: Array<String>) {
    runApplication<InspektorApplication>(*args)
}
