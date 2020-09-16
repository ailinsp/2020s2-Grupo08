package org.unq.ui.User

import org.unq.ui.model.Postmodel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeletePostView (owner: WindowOwner, model: Postmodel): Dialog<Postmodel>(owner, model){
    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel) with {
            text = "Are you sure to delete Post with id : ${modelObject.id} ?"
        }


        Button(mainPanel) with{
            caption ="Yes"
            onClick{
                accept()
            }
        }

        Button(mainPanel) with{
            caption ="No"
            onClick{
                cancel()
            }
        }

    }

}
