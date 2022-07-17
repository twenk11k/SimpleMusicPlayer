package com.twenk11k.simplemusicplayer.view.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collectWhenStarted(
    flow: Flow<T>,
    firstTimeDelay: Long = 0L,
    action: suspend (value: T) -> Unit
) {
    lifecycleScope.launch {
        delay(firstTimeDelay)
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(action)
        }
    }
}

fun Fragment.showSnackBar(failure: Int, timeLength: Int) {
    Snackbar.make(requireView(), failure, timeLength).show()
}
