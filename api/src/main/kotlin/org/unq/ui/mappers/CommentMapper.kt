package org.unq.ui.mappers

data class CommentMapper (
        val id : String? = null,
        val body: String? = null,
        val user : UserMapper? = null
)
