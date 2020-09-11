package org.unq.ui.Login

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel(val instagramSystem: InstagramSystem = getInstagramSystem()){
    var email = ""
    var password = ""

    fun login(email: String, password: String){
        instagramSystem.login(email, password)
    }

}