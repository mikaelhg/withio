package io.mikael.withio

import java.io.Closeable
import java.io.IOException

interface InputOutputBase<A: Closeable, B: Closeable> {

    fun input(): A

    fun output(): B

    @Throws(InputOutputException::class)
    fun close() {
        val inputException = try {
            input().close()
            null
        } catch (e: IOException) {
            e
        }
        val outputException = try {
            output().close()
            null
        } catch (e: IOException) {
            e
        }
        if (inputException != null || outputException != null) {
            throw InputOutputException(inputException, outputException)
        }
    }

}