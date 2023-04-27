package com.example.edamatest.ui

sealed interface StateFlowStatus<out T> {

    class Active<T : Any>(val model: T) : StateFlowStatus<T>

    object Empty : StateFlowStatus<Nothing>

}