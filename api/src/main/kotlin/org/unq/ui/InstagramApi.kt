package org.unq.ui

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin
import org.unq.ui.bootstrap.getInstagramSystem

fun main() { InstagramApi(7000).init() }

class InstagramApi(private val port: Int) {
    fun init(): Javalin {
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
        }
        app.start(port)

        val instagramController = InstagramController(getInstagramSystem())

        app.routes {
            path("register") {
                post(instagramController::register)
            }
            path("login") {
                post(instagramController::login)
            }
            path("user") {
                get(instagramController::getLoggedUser)
                path(":userId") {
                    get(instagramController::getUserById)
                    path("follow") {
                        put(instagramController::updateFollowerById)
                    }
                }
            }
            path("post/:postId") {
                get(instagramController::getPostById)
                path("like") {
                    put(instagramController::updateLikesById)
                }
                path("comment") {
                    post(instagramController::addCommentById)
                }
            }
        }

        return app
    }
}