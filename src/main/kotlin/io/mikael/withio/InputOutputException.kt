package io.mikael.withio

import java.io.IOException

class InputOutputException(
    val inputException: IOException?,
    val outputException: IOException?
) : IOException()