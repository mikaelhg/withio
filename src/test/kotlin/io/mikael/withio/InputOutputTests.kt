package io.mikael.withio

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.StringReader
import java.io.StringWriter

class InputOutputTests {

    @Test
    fun inputOutput() {
        val input = "foobar"
        val a = StringReader(input)
        val b = StringWriter()
        InputOutput(a, b).use { io ->
            val data = io.input.readText()
            io.output.write(data)
        }
        Assertions.assertEquals(input, b.buffer.toString())
    }

}