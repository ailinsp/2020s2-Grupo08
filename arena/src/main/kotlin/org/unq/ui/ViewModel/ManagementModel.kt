package org.unq.ui.ViewModel

import org.unq.ui.bootstrap.getInstagramSystem
import org.unq.ui.model.InstagramSystem
import org.uqbar.commons.model.annotations.Observable


@Observable
class ManagementModel(var dominio: InstagramSystem = getInstagramSystem()){

    var userLogged :String? = null;





    fun saveLog(email : String){
        userLogged = email
    }
}