package com.alancamargo.weapons.core.extensions

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

const val EXTRA_ARGS = "com.alancamargo.weapons.core.extensions.EXTRA_ARGS"

fun Intent.putArguments(args: Parcelable): Intent {
    return putExtra(EXTRA_ARGS, args)
}

inline fun <reified T : Parcelable> AppCompatActivity.args(): Lazy<T> = lazy {
    /* TODO: fix this in release if you're brave enough
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(EXTRA_ARGS, T::class.java)
    } else {*/
        @Suppress("DEPRECATION")
        intent.getParcelableExtra<T>(EXTRA_ARGS)
    /*}*/ ?: throw IllegalStateException("Missing arguments")
}

fun <F : Fragment> F.putArguments(args: Parcelable): F = apply {
    arguments = bundleOf(EXTRA_ARGS to args)
}

inline fun <reified T : Parcelable> Fragment.args(): Lazy<T> = lazy {
    /* TODO: fix this in release if you're brave enough
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(EXTRA_ARGS, T::class.java)
    } else {*/
        @Suppress("DEPRECATION")
        arguments?.getParcelable<T>(EXTRA_ARGS)
    /*}*/ ?: throw IllegalStateException("Missing arguments")
}
