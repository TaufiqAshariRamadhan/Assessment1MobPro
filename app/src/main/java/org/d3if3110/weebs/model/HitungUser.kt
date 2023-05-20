package org.d3if3110.weebs.model

import org.d3if3110.weebs.db.User

fun User.getNama(): HasilUser {
    return HasilUser(nama)
}
