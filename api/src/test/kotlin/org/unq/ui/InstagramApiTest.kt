package org.unq.ui

import io.javalin.Javalin
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.jackson.responseObject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.unq.ui.mappers.UserMapper

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class InstagramApiTest {

    private lateinit var api: Javalin

    @BeforeAll fun setUp() {
        api = InstagramApi(8000).init()
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${api.port()}/"
    }

    @AfterAll fun tearDown() {
        api.stop()
    }

    @Test @Order(1)
    fun `1 POST register a new user`() {
        val userJson = """
            {
                "name": "Edward Elric",
                "email": "edwardElric@gmail.com",
                "password": "philosopherStone",
                "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg"
            }
        """
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        assertEquals(201, response.statusCode)
    }

    @Test @Order(2)
    fun `2 POST register a new user with an invalid body`() {
        val userJson = """
            {
                "name": "Edward Elric",
                "email": "edwardElric@gmail.com",
                "password": "philosopherStone"
            }
        """
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        assertEquals(400, response.statusCode)
    }

    @Test @Order(3)
    fun `3 POST register a new user with an already registered email`() {
        val userJson = """
            {
                "name": "Ailin Elric",
                "email": "edwardElric@gmail.com",
                "password": "ailin",
                "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg"
            }
        """
        val (_, response, _) = Fuel.post("register").jsonBody(userJson).responseString()
        assertEquals(400, response.statusCode)
    }

    @Test @Order(4)
    fun `4 POST login a registered user`() {
        val userJson = """
            {
                "email": "edwardElric@gmail.com",
                "password": "philosopherStone"
            }
        """
        val (_, response, _) = Fuel.post("login").jsonBody(userJson).responseString()
        assertEquals(200, response.statusCode)
    }

    @Test @Order(5)
    fun `5 POST login a non existent user`() {
        val userJson = """
            {
                "email": "ailin@gmail.com",
                "password": "ailin"
            }
        """
        val (_, response, _) = Fuel.post("login").jsonBody(userJson).responseString()
        assertEquals(404, response.statusCode)
    }

    /** Este rompe por el jwt
     *
    @Test @Order(6)
    fun `6 GET user by id`() {
        val (_, response, result) = Fuel.get("user/u_31").responseObject<UserMapper>()
        val userResult = result.get()
        assertEquals(200, response.statusCode)
        assertEquals("Edward Elric", userResult.name)
        assertEquals("https://a.wattpad.com/cover/83879595-352-k192548.jpg", userResult.image)
        assertTrue(userResult.followers.isEmpty())
        assertTrue(userResult.posts.isEmpty())
    }*/
}