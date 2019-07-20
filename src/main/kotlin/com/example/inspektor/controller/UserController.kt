package com.example.inspektor.controller

import com.example.inspektor.models.entities.User
import com.example.inspektor.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userRepository: UserRepository) {

    @GetMapping
    fun getUsers() = userRepository.findAll()

    @PostMapping
    fun addUser(@RequestBody user: User) = userRepository.save(user)

    @GetMapping("locations")
    fun getAllLocations(): List<String> = userRepository.getLocations()

//    @GetMapping("/{location}/departments")
//    fun getDepartmentsForLocation(@PathVariable("location") location: String): List<String> =
//            userRepository.findByLocationIgnoreCase(location)
}