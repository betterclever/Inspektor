package com.example.inspektor

import com.example.inspektor.services.FileUploadService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.kotlin.coroutine.EnableCoroutine

// Wifi: Headout-Atithi / ++
// Password: AtithiDevoBhava134

@SpringBootApplication
@EnableCoroutine
class InspektorApplication {
}

fun main(args: Array<String>) {
    runApplication<InspektorApplication>(*args) {
        addInitializers(beans {
            bean<FileUploadService>()
        })
    }
}
