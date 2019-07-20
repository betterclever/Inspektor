package com.example.inspektor.models.dto

data class ObservationCreateDto (
        val assignedToUsers: List<Long>,
        val title: String,
        val urgent: Boolean = false,
        val photos: List<String>,
        val submittedBy: Long
)