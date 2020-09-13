package org.unq.ui.User

import org.unq.ui.model.DraftPostModel
import org.uqbar.arena.kotlin.extensions.bindTo
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.Dialog


class EditPostView(owner: WindowOwner, model: DraftPostModel): Dialog<DraftPostModel>(owner, model){

    override fun createFormPanel(mainPanel: Panel) {
        title = "Editar Nota"

        Label(mainPanel) with {
            text ="Portrait"
        }
        TextBox(mainPanel) with{
            bindTo("portrait")
        }

        Label(mainPanel) with {
            text ="Landscape"
        }
        TextBox(mainPanel) with{
            bindTo("landscape")
        }

        Label(mainPanel) with {
            text ="Description"
        }
        TextBox(mainPanel) with{
            bindTo("description")
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
