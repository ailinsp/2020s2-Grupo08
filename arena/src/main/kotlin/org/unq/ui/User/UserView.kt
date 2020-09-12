package org.unq.ui.User


import org.unq.ui.model.Postmodel
import org.unq.ui.model.UserModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable
import sun.security.x509.AccessDescription


class UserView(owner: WindowOwner, model: UserModel): SimpleWindow<UserModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title =  "Publicaciones del usuario"
        setMinWidth(300)

       // Label(mainPanel) withText "ID:  ${modelObject.user!!.id}"
       // Label(mainPanel) withText "NAME: ${modelObject.user!!.name}"
       // Label(mainPanel) withText "EMAIL: ${modelObject.user!!.email}"

        table<Postmodel>(mainPanel) {
            bindItemsTo("posts")
            bindSelectionTo("selected")
            visibleRows = 10


            column {
                title = "#"
                fixedSize = 30
                bindContentsTo("id")
            }
             column {
                title = "Descripcion"
                fixedSize = 130
                bindContentsTo("description")
            }

            column {
                title = "LandScape"
                fixedSize = 130
                bindContentsTo("landscape")
            }
            column {
                title = "Portrait"
                fixedSize = 230
                bindContentsTo("portrait")
            }
        }


    }


















}