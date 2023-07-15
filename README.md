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
val a = Files.newBufferedReader(inputFile)
val b = Files.newBufferedWriter(outputFile)
InputOutput(a, b).use { io ->
    val data = io.input.readText()
    io.output.write(data)
}

withInputOutput()
    .withInput { Files.newBufferedReader(inputFile) }
    .withOutput { Files.newBufferedWriter(outputFile) }
    .use { io ->
        val data = io.input.readText()
        io.output.write(data)
    }
```
