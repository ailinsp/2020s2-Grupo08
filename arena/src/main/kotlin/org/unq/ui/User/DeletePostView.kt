package org.unq.ui.User

import org.unq.ui.model.InstagramModel
import org.unq.ui.model.Postmodel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class DeletePostView (owner: WindowOwner, model: Postmodel): Dialog<Postmodel>(owner, model){
    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel) with {
            text ="Borrar Post con id"
        }
        TextBox(mainPanel) with{
            bindTo("id")
        }

        Button(mainPanel) with{
            caption ="Guardar"
            onClick{
                accept()

            }
        }

        Button(mainPanel) with{
            caption ="Cancelar"
            onClick{
                cancel()
            }
        }

    }

}
