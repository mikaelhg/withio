package io.mikael.withio

import java.io.Closeable
import java.io.IOException

class InputOutput<A: Closeable, B: Closeable>(var input: A, var output: B): Closeable {

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