package com.example.inspektor.models.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Observation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val title: String,
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val urgent: Boolean,
        @ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="submitted_by")
        val submittedBy: User,
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "observation_assignee",
                joinColumns = [JoinColumn(name = "observation_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "phone")])
        val assignees: List<User> = emptyList(),
        @ElementCollection
        val photos: List<String> = emptyList()
)