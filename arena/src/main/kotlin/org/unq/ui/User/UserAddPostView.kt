package org.unq.ui.User

import org.unq.ui.model.InstagramModel
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class UserAddPostView (owner: WindowOwner, model: InstagramModel): SimpleWindow<InstagramModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(mainPanel: Panel) {
        title = "Add and Edit Post"
        setMinWidth(300)

        Panel(mainPanel) with {
            asColumns(4)
            Label(it) withText "Portrait"
            TextBox(it) with {
                width = 150
                bindTo("portrait")
            }

            asColumns(2)
            Label(it) withText "Landscape"
            TextBox(it) with {
                width = 150
                bindTo("landscape")
            }

            asColumns(2)
            Label(it) withText "Description"
            TextBox(it) with {
                width = 150
                bindTo("description")
            }
        }

        Panel(mainPanel) with {
            asColumns(2)
            Button(it) with {
                caption = "Accept"
                width = 175
                onClick {

                }

            }

            Button(it) with {
                caption = "Cancel"
                width = 175
                onClick { }
            }
        }
    }

}