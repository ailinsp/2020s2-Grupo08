package org.unq.ui.model

import org.omg.CORBA.UserException

class UsedEmail : Exception("Email used")

class RepeatedTitle : Exception("Title used")

class NotFound(msg: String) : Exception("Not found $msg")

class NotATag : Exception("Missing #")

class InvalidUserOPassword(msg:String): UserException()