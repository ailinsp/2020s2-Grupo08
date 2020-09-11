package org.unq.ui.User

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserModel(val instagramSystem: InstagramSystem = getInstagramSystem()) {

    fun search(msg: String) {
        instagramSystem.searchByTag(msg)
    }

}