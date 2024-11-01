package com.example.demo.controller

import com.example.demo.model.Person
import com.example.demo.service.PersonService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons", produces = [MediaType.APPLICATION_JSON_VALUE])
class PersonController(
    val personService: PersonService,
) {

    @GetMapping("/{id}")
    suspend fun get(@PathVariable id: String): Person {
        return personService.getPersonSuspend()

    }
}