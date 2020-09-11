package org.unq.ui.model

import org.unq.ui.bootstrap.getInstagramSystem
import org.uqbar.arena.Application
import org.uqbar.arena.widgets.*
import org.uqbar.arena.kotlin.*
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable


class LoginWindow(owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {
        TODO("Not yet implemented")
    }
    override fun createFormPanel(mainPanel: Panel?) {
        title = "Login"

    }

    override fun createContents(mainPanel: Panel) {
        title = "Celsius => Fahrenheit"

        Label(mainPanel) withText "Celsius"


        NumericField(mainPanel) with {
            bindTo("celsius")
        }


        Button(mainPanel) with {
            caption = "Transform"
            onClick { modelObject.transform() }
        }

        Label(mainPanel) withText "Fahrenheit"

        NumericField(mainPanel) with {
            bindTo("fahrenheit")
        }

    }
}

@Observable
class InstagramModel(val instagramSystem: InstagramSystem = getInstagramSystem()){

}

class InstagramApplication: Application(){
    override fun createMainWindow(): Window<*> {
        return LoginWindow(this, InstagramModel())
    }
}

fun main(){
    InstagramApplication().start()
}