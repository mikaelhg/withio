package io.mikael.withio

import java.io.Closeable

class InputOutput<A: Closeable, B: Closeable>(input: A, output: B) : InputOutputBase<A, B>() {

    init {
        this.input = input
        this.output = output
    }

}