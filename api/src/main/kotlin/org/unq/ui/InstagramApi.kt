package org.unq.ui

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.security.Role
import io.javalin.core.util.RouteOverviewPlugin
import org.unq.ui.bootstrap.getInstagramSystem

fun main() { InstagramApi(7000).init() }

enum class InstagramRoles: Role {
    ANYONE, USER
}

class InstagramApi(private val port: Int) {
    fun init(): Javalin {

        val instagramSystem = getInstagramSystem()

        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
            it.accessManager(InstagramAccessManager(instagramSystem))
        }
        app.start(port)

        val instagramController = InstagramController(instagramSystem)
        val userController = UserController(instagramSystem)

        app.routes {
            path("register") {
                post(userController::register, setOf(InstagramRoles.ANYONE))
            }
            path("login") {
                post(userController::login, setOf(InstagramRoles.ANYONE))
            }
            path("user") {
                get(userController::getLoggedUser, setOf(InstagramRoles.USER))
                path(":userId") {
                    get(userController::getUserById, setOf(InstagramRoles.USER))
                    path("follow") {
                        put(userController::updateFollowerById, setOf(InstagramRoles.USER))
                    }
                }
            }
            path("post/:postId") {
                get(instagramController::getPostById, setOf(InstagramRoles.USER))
                path("like") {
                    put(instagramController::updateLikesById, setOf(InstagramRoles.USER))
                }
                path("comment") {
                    post(instagramController::addCommentById, setOf(InstagramRoles.USER))
                }
            }
            path("search") {
                get(instagramController::searchTagOrUser, setOf(InstagramRoles.USER))
            }
        }
        return app
    }


}