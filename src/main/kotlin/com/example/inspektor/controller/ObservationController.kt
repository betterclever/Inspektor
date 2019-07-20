package com.example.inspektor.controller

import com.example.inspektor.models.dto.ObservationCreateDto
import com.example.inspektor.models.entities.Observation
import com.example.inspektor.repository.ObservationRepository
import com.example.inspektor.repository.UserRepository
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/view/{id}", produces = [MediaType.TEXT_HTML_VALUE])
    fun getObservationView(@PathVariable("id") id: Long): String {
        val observation = observationRepository.findById(id).get()
        return createHTMLDocument()
                .html {
                    body {
                        div {
                            h1 {
                                +observation.title
                            }
                            br
                            div {
                                b { +"Submitted By: "}
                                +observation.submittedBy.name
                            }
                            observation.photos.map {
                                img {
                                    src = it
                                }
                            }
                        }
                    }
                }.serialize()
    }
}