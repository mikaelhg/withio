package io.mikael.withio

import java.io.Closeable

fun <T: Closeable, U: Closeable> withInputOutput(): InputOutputBuilder<T, U> {
    return InputOutputBuilder()
}

class InputOutputBuilder<A: Closeable, B: Closeable> : InputOutputBase<A, B> {

    lateinit var input: A

    lateinit var output: B

    fun <R> use(function: (InputOutputBuilder<A, B>) -> R): InputOutputBuilder<A, B> {
        try {
            function(this)
            return this
        } finally {
            close()
        }
    }

    fun <R> then(function: (InputOutputBuilder<A, B>) -> R): InputOutputBuilder<A, B> {
        function(this)
        return this
    }

    fun withInput(function: () -> A): InputOutputBuilder<A, B> {
        input = function()
        return this
    }

    fun withOutput(function: () -> B): InputOutputBuilder<A, B> {
        output = function()
        return this
    }

    override fun input(): A = input

    override fun output(): B = output

}
