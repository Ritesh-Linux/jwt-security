package com.example.jwtdemo.repository

import com.example.jwtdemo.model.Role
import com.example.jwtdemo.model.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository {

    private val users = mutableSetOf(
        User(
            id = UUID.fromString("9abd3663-05d3-4cc3-bedf-c623d1622b05"),
            email = "ritesh@gmail.com",
            password = "\$2a\$10\$F60a.nlOOcoTaO0lAPym5O7NQVi9nssYrk6HpHmPxDkpYORBYFXt2",
            role = Role.USER,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-1@gmail.com",
            password = "pass1",
            role = Role.USER,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-2@gmail.com",
            password = "pass2",
            role = Role.ADMIN,
        ),
        User(
            id = UUID.randomUUID(),
            email = "email-3@gmail.com",
            password = "pass3",
            role = Role.USER,
        ),
    )

    fun save(user: User): Boolean =
        users.add(user)

    fun findByEmail(email: String): User? =
        users
            .firstOrNull { it.email == email }

    fun findAll(): Set<User> =
        users

    fun findByUUID(uuid: UUID): User? =
        users
            .firstOrNull { it.id == uuid }

    fun deleteByUUID(uuid: UUID): Boolean {
        val foundUser = findByUUID(uuid)

        return foundUser?.let {
            users.removeIf {
                it.id == uuid
            }
        } ?: false
    }
}