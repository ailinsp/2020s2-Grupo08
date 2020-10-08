package org.unq.ui.Exceptions

import org.omg.CORBA.UserException

class InvalidUserOPassword(): UserException()

class FieldsBlank() : Exception("Both fields are required")