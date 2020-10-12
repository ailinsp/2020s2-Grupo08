package org.unq.ui.ViewModel

import org.unq.ui.Exceptions.InvalidUserOPassword
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException


@Observable
class RegisterModel(var managementModel : ManagementModel) {

    var password = ""
    var name = ""
    var email = ""
    var image = ""

    /**
     * Register and logs in a user into the system.
     * @param name It's the user name to register.
     * @param email It's the user email to register.
     * @param password It's the user password to register.
     * @param image It's the user image to register.
     */
    fun register(name: String, email: String, password: String, image: String){
        if(name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || image.isNullOrEmpty()){
            throw UserException("All fields must be completed")
        }
        if(!email.contains("@")){
            throw InvalidUserOPassword()
        }
        managementModel.dominio.register(name,email, password, image)
    }

    fun saveLog(email : String){
        managementModel.saveLog(email)
    }



}

