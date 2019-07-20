package com.example.inspektor.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class User (
        @Id
        val phone: Long,
        val department: String,
        val designation: String,
        val location: String,
        val name: String,
        val assignable: Boolean,
        @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
        @JoinTable(name = "observation_assignee",
                inverseJoinColumns = [JoinColumn(name = "observation_id", referencedColumnName = "id")],
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "phone")])
        @get:JsonIgnore
        val observations: List<Observation> = emptyList()
)