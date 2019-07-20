package com.example.inspektor.controller

import com.example.inspektor.models.dto.ObservationCreateDto
import com.example.inspektor.models.entities.Observation
import com.example.inspektor.repository.ObservationRepository
import com.example.inspektor.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/observations")
class ObservationController(
        val userRepository: UserRepository,
        val observationRepository: ObservationRepository
) {
    @PostMapping
    fun addNewObservation(@RequestBody observationCreateDTO: ObservationCreateDto): Observation {
        val users = userRepository.findAllById(observationCreateDTO.assignedToUsers)
        val submittedBy = userRepository.findById(observationCreateDTO.submittedBy)
        val observation = Observation(
                title = observationCreateDTO.title,
                assignees = users,
                submittedBy = submittedBy.get(),
                photos = observationCreateDTO.photos,
                urgent = observationCreateDTO.urgent
        )
        return observationRepository.save(observation)
    }
}