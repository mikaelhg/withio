package io.mikael.withio

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.StringReader
import java.io.StringWriter
import java.nio.file.Files
import java.nio.file.Paths

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

    @Test
    fun stringBuilder() {
        val input = "foobar"
        withInputOutput<StringReader, StringWriter>()
            .withInput { spy(StringReader(input)) }
            .withOutput { spy(StringWriter()) }
            .use { io ->
                io.output.write(io.input.readText())
            }
            .then { io ->
                Assertions.assertEquals(input, io.output.buffer.toString())
                verify(io.input, times(1)).close()
                verify(io.output, times(1)).close()
            }
    }

    @Test
    fun bufferedBuilder() {
        val inputFile = Paths.get("src/test/resources/input.1")
        val outputFile = Paths.get("build/tmp/test/output.1")
        withInputOutput<BufferedReader, BufferedWriter>()
            .withInput { Files.newBufferedReader(inputFile) }
            .withOutput { Files.newBufferedWriter(outputFile) }
            .use { io ->
                val data = io.input.readText()
                io.output.write(data)
            }
    }

}
