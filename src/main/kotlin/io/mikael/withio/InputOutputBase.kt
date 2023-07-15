package io.mikael.withio

import java.io.Closeable
import java.io.IOException

abstract class InputOutputBase<A: Closeable, B: Closeable> : Closeable {

    lateinit var input: A

    lateinit var output: B

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