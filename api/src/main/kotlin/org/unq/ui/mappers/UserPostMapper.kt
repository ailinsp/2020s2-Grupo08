package org.unq.ui.mappers

import org.unq.ui.model.Comment
import org.unq.ui.model.Post
import org.unq.ui.model.User
import java.time.LocalDateTime

data class UserPostMapper(
    val description: String? = null,
    val portrait:String? = null,
    val landscape:String? = null
   )

