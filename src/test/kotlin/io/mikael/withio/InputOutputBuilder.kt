package io.mikael.withio

import java.io.Closeable

fun <T: Closeable, U: Closeable> withInputOutput(): InputOutputBuilder<T, U> {
    return InputOutputBuilder()
}

class InputOutputBuilder<A: Closeable, B: Closeable> : InputOutputBase<A, B>() {

    fun withInput(function: () -> A): InputOutputBuilder<A, B> {
        input = function()
        return this
    }

    fun withOutput(function: () -> B): InputOutputBuilder<A, B> {
        output = function()
        return this
    }

}
