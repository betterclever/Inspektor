package com.example.inspektor.repository

import com.example.inspektor.models.entities.Observation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ObservationRepository: JpaRepository<Observation, Long> {

}