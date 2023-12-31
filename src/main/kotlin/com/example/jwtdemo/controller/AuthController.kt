package com.example.jwtdemo.controller

import com.example.jwtdemo.service.AuthenticationService
import com.example.jwtdemo.utils.AuthenticationRequest
import com.example.jwtdemo.utils.AuthenticationResponse
import com.example.jwtdemo.utils.RefreshTokenRequest
import com.example.jwtdemo.utils.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun authenticate(
        @RequestBody authRequest: AuthenticationRequest
    ): AuthenticationResponse = run {

        println(authRequest.email)
        println(authRequest.password)
        authenticationService.authentication(authRequest)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest
    ): TokenResponse {

        println("refresh:- $request")

        return authenticationService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")
    }

    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this
        )

}