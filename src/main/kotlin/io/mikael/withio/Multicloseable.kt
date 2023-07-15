package io.mikael.withio

import java.io.Closeable
import java.io.IOException

class Multicloseable(private vararg val closeables: Closeable) : Closeable {

    private val ioExceptions = mutableListOf<IOException>()

    override fun close() {
        closeables.forEach(::closeIt)
    }

    private fun closeIt(closeable: Closeable) {
        try {
            closeable.close()
        } catch (e: IOException) {
            ioExceptions.add(e)
        }
    }

}