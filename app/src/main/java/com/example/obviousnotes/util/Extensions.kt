package com.example.obviousnotes.util

import com.example.obviousnotes.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

fun GoogleSignInAccount.toUser() =
    User(
        id = this.id!!,
        name = this.displayName!!,
        email = this.email!!
    )