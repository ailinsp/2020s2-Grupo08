package org.unq.ui

import io.javalin.http.Context
import org.unq.ui.Token.TokenController
import org.unq.ui.mappers.UserLoginMapper
import org.unq.ui.mappers.UserRegisterMapper
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.UsedEmail


class UserController(val system: InstagramSystem) {

    val tokenController = TokenController()
    /**
     * Registra a un usuario
     */
    fun register(ctx: Context) {
        val newUser = ctx.bodyValidator<UserRegisterMapper>()
            .check(
                {
                    it.name != null && it.email != null && it.password != null && it.image != null
                },
                "Invalid body: name, email, password and image should not be null"
            )
            .get()

        try {
            val user = system.register(newUser.name!!, newUser.email!!, newUser.password!!, newUser.image!!)
            ctx.header("Authorization", tokenController.genereteToken(user))
            ctx.status(201).json(ResultResponse("Ok"))
        } catch (e: UsedEmail) {
            ctx.status(400).json(ResultResponse(e.message!!))
        }
    }

    /**
     * Logea a un usuario
     */
    fun login(ctx: Context) {
        val user = ctx.bodyValidator<UserLoginMapper>()
            .check(
                {
                    it.email != null && it.password != null
                },
                "Invalid body: email and password should not be null"
            )
            .get()

        try {
            val user = system.login(user.email!!, user.password!!)
            ctx.header("Authorization", tokenController.genereteToken(user))
            ctx.status(200).json(ResultResponse("Ok"))
        } catch (e: NotFound) {
            ctx.status(404).json(MessageResponse("error", "User not found"))
        }
    }

    /**
     * Retorna al usuario logueado con su timeline
     */
    fun getLoggedUser(ctx: Context) {}


}
