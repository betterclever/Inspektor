package com.example.inspektor.repository

import com.example.inspektor.models.entities.Observation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ObservationRepository: JpaRepository<Observation, Long> {

    @Query(value = "SELECT o from Observation o where o.submittedBy.phone = :userId")
    fun getObservationBySubmittedBy(@Param("userId") userId: Long): List<Observation>
}