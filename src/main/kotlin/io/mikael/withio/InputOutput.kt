package io.mikael.withio

import java.io.Closeable
import java.io.IOException

class InputOutput<A: Closeable, B: Closeable>(var input: A, var output: B): Closeable {

    companion object {
        fun <C: Closeable, D: Closeable> inputOutput(input: C, output: D): InputOutput<C, D> {
            return InputOutput(input, output)
        }
    }

    fun withInput(newInput: A): InputOutput<A, B> {
        this.input = newInput
        return this
    }

    fun withInput(newInput: () -> A): InputOutput<A, B> {
        this.input = newInput()
        return this
    }

    fun withOutput(newOutput: B): InputOutput<A, B> {
        this.output = newOutput
        return this
    }

    fun withOutput(newOutput: () -> B): InputOutput<A, B> {
        this.output = newOutput()
        return this
    }

    @Throws(InputOutputException::class)
    override fun close() {
        val inputException = try {
            input.close()
            null
        } catch (e: IOException) {
            e
        }
        val outputException = try {
            output.close()
            null
        } catch (e: IOException) {
            e
        }
        if (inputException != null || outputException != null) {
            throw InputOutputException(inputException, outputException)
        }
    }

}