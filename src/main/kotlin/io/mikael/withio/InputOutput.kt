package io.mikael.withio

import java.io.Closeable

class InputOutput<A: Closeable, B: Closeable>(input: A, output: B)
    : InputOutputBase<A, B>, Closeable {

    val input: A

    val output: B

    init {
        this.input = input
        this.output = output
    }

    override fun input(): A = input

    override fun output(): B = output
    override fun close() = super.close()

}