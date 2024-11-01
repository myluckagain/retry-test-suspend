package com.example.demo.service

import com.example.demo.model.Person
import kotlinx.coroutines.runBlocking
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
@EnableRetry
class PersonService {
    @Retryable(maxAttempts = 3,backoff =  Backoff(delay = 1000))
    suspend fun getPersonSuspend(): Person {
        val random = (1..2).random()
        println("random=$random,  ${if (random == 1) "exception" else "success"} ")
        if (random == 1) throw IllegalStateException("Exception occurred")

        return Person("Name")

    }

    @Retryable(maxAttempts = 3, backoff =  Backoff(delay = 1000) )
    fun getPerson(): Person {
        val random = (1..2).random()
        return runBlocking {

            println("random=$random,  ${if (random == 1) "exception" else "success"} ")

            if (random == 1) throw IllegalStateException("Exception occurred")

            Person("Name")
        }
    }


}