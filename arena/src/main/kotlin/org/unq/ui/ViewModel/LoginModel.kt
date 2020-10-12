package org.unq.ui.ViewModel

import org.unq.ui.Exceptions.FieldsBlank
import org.unq.ui.Exceptions.InvalidUserOPassword
import org.uqbar.commons.model.annotations.Observable

@Observable
class LoginModel(var managementModel : ManagementModel){

    var password = ""
    var email = ""


    /**
     * Logs in a user into the system.
     * @param email It's the user email to log in.
     * @param password It's the user password to log in.
     * @throws FieldsBlank When the inputs are incomplete.
     * @throws InvalidUserOPassword When the email input is invalid.
     */
    fun login(email: String, password: String) {
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            throw FieldsBlank()
        }else {
            if (!email.contains("@")) {
                throw InvalidUserOPassword()
            }
        }
        managementModel.dominio.login(email,password)
        saveLog(email)
    }



    fun saveLog(email : String){
        managementModel.saveLog(email)

    }


}