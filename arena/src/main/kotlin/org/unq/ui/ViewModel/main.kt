package org.unq.ui.ViewModel


import org.unq.ui.View.LoginWindow
import org.uqbar.arena.Application
import org.uqbar.arena.windows.*

class InstagramApplication: Application(){
    override fun createMainWindow(): Window<*> {
        return LoginWindow(this, InstagramModel())
    }
}

fun main(){
    InstagramApplication().start()
}