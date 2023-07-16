# With IO

[![](https://jitpack.io/v/mikaelhg/withio.svg)](https://jitpack.io/#mikaelhg/withio)

A quick idea turned into a Kotlin mini-library for this Java pattern:

```java
try (var input = Files.newBufferedReader(inputFile);
     var output = Files.newBufferedWriter(outputFile))
{
        /* ... */
}
```

... and with this library ...

```kotlin
import io.mikael.withio.InputOutput

val a = Files.newBufferedReader(inputFile)
val b = Files.newBufferedWriter(outputFile)
InputOutput(a, b).use { io ->
    val data = io.input.readText()
    io.output.write(data)
}
```

```kotlin
import io.mikael.withio.withInputOutput

withInputOutput<BufferedReader, BufferedWriter>()
    .withInput { Files.newBufferedReader(inputFile) }
    .withOutput { Files.newBufferedWriter(outputFile) }
    .use { io ->
        val data = io.input.readText()
        io.output.write(data)
    }
    .then { io ->
        /* if you still need the input and output for something */
    }
```
