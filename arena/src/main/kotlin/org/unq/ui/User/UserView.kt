package org.unq.ui.User


import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class UserView(owner: WindowOwner, model: UserModel): SimpleWindow<UserModel>(owner, model){
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(p0: Panel?) {
        title = "Post de User"
        setMinWidth(300)
    }

}