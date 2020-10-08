package org.unq.ui.token

import org.junit.Test
import org.unq.ui.model.User
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TokenJWTTest {

    val user = User("u_31", "Ailin", "ailin@gmail.com", "ailin", "image.jpg", mutableListOf())
    val user2 = User("u_50", "Pepito", "pepito@gmail.com", "pepito", "image.jpg", mutableListOf())


    @Test
    fun testGenerateToken() {
        val tokenJWT = TokenJWT()
        assertEquals(tokenJWT.generateToken(user), "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6InVfMzEifQ.5SOdVvvWTwwdf6_6n9woiqOTOV2mpfgt2ZveQMnc2ZI")
    }

    @Test
    fun testGenerateTokenWithAnotherUser() {
        val tokenJWT = TokenJWT()
        assertNotEquals(tokenJWT.generateToken(user), tokenJWT.generateToken(user2))
        assertEquals(tokenJWT.generateToken(user2), "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6InVfNTAifQ.0ihJS-WMskagv-jru-OPjyUMD-UqUexjzUMs-wgGK3k")
    }

    @Test
    fun testValidateToken() {
        val tokenJWT = TokenJWT()
        assertEquals(tokenJWT.validateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6InVfMzEifQ.5SOdVvvWTwwdf6_6n9woiqOTOV2mpfgt2ZveQMnc2ZI"), "u_31")
    }

    @Test(expected = NotValidToken::class)
    fun testValidateTokenWithInvalidToken() {
        val tokenJWT = TokenJWT()
        tokenJWT.validateToken("asdf")
    }

}