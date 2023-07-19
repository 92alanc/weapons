package com.alancamargo.weapons.core.extensions

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity

private const val EXTRA_ARGS = "com.alancamargo.weapons.core.extensions.EXTRA_ARGS"

fun Intent.putArguments(args: Parcelable): Intent {
    return putExtra(EXTRA_ARGS, args)
}

fun <T : Parcelable> AppCompatActivity.args(): Lazy<T> = lazy {
    intent.getParcelableExtra(EXTRA_ARGS) as? T ?: throw IllegalStateException(
        "Missing arguments"
    )
}
