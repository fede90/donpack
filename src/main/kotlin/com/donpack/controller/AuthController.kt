package com.donpack.controller

import com.donpack.dto.ApiException
import com.donpack.dto.LoginDto
import com.donpack.dto.LoginResponseDto
import com.donpack.dto.RegisterDto
import com.donpack.model.User
import com.donpack.service.HashService
import com.donpack.service.TokenService
import com.donpack.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This controller handles login and register requests.
 * Both routes are public as specified in SecurityConfig.
 */
@RestController
@RequestMapping("/api/public")
class AuthController(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByName(payload.name) ?: throw ApiException(401, "Login failed. Wrong credentials")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ApiException(401, "Login failed. Wrong credentials")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.existsByName(payload.name)) {
            throw ApiException(400, "Name already exists")
        }

        val user = User(
            name = payload.name,
            password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}
