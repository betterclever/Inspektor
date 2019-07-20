package com.example.inspektor.repository

import com.example.inspektor.models.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u.location from User u")
    fun getLocations(): List<String>
}