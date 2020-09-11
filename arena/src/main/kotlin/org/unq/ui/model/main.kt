package org.unq.ui.model

import org.unq.ui.Login.LoginModel
import org.unq.ui.Login.LoginView
import org.uqbar.arena.Application
import org.uqbar.arena.windows.*



class InstagramApplication: Application(){
    override fun createMainWindow(): Window<*> {
        return LoginView(this, LoginModel())
    }
}

fun main(){
    InstagramApplication().start()
}