package org.unq.ui

import io.javalin.core.security.AccessManager
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse
import org.unq.ui.token.NotFoundToken
import org.unq.ui.token.TokenJWT
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.NotFound
import org.unq.ui.model.User


class InstagramAccessManager(val instagramSystem: InstagramSystem) : AccessManager {

    val tokenController = TokenJWT()



    fun getUser(token: String): User {
        try {
            val userId = tokenController.validateToken(token)
            return instagramSystem.getUser(userId)
        } catch (e: NotFoundToken) {
            throw UnauthorizedResponse("Token not found/Invalid")
        } catch (e: NotFound) {
            throw UnauthorizedResponse("Invalid Token/User not logged")
        }
    }

    override fun manage(handler: Handler, ctx: Context, roles: MutableSet<Role>) {
        val token = ctx.header("Authorization")
        when {
            roles.contains(InstagramRoles.ANYONE) -> handler.handle(ctx)
            token === null -> throw UnauthorizedResponse()
            roles.contains(InstagramRoles.USER) -> {
                    getUser(token)
                    handler.handle(ctx)

            }
        }
    }
}