package org.unq.ui.mappers

data class UserSearchMapper (
        val name: String? = null,
        val image: String? = null,
        val followers: MutableList<UserMapper>,
        val id: String? = null
)