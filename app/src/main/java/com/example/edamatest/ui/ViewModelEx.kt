package com.example.edamatest.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.launchAndCollectWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit
) = flowWithLifecycle(lifecycleOwner.lifecycle, state)
    .onEach(action)
    .launchIn(lifecycleOwner.lifecycleScope)