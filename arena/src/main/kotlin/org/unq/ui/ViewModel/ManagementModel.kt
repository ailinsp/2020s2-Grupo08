package org.unq.ui.ViewModel

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.DraftPost
import org.unq.ui.model.InstagramSystem
import org.unq.ui.model.User
import org.uqbar.commons.model.annotations.Observable


@Observable
class ManagementModel(var dominio: InstagramSystem = getInstagramSystem()){

    var userLogged :User? = null;

    fun login(email: String, password: String){
        userLogged = dominio.login(email, password)
    }

    fun editProfile(name: String, password: String, image: String) {
        dominio.editProfile(userLogged!!.id, name, password, image)
    }

    fun addPost(draftPost: DraftPost) {
        dominio.addPost(userLogged!!.id, draftPost)
    }

}