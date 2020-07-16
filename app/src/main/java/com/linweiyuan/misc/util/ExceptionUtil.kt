package com.linweiyuan.misc.util

import com.linweiyuan.misc.model.Border
import com.linweiyuan.misc.model.Color
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

object ExceptionUtil {
    private val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun get(t: Throwable, color: Color = Color.ANSI_RED_BACKGROUND, filter: String? = null, limit: Int? = null): String {
        val sb = StringBuilder()

        var stackTraceStream = Arrays.stream(t.stackTrace).map(StackTraceElement::toString)

        if (filter != null) {
            stackTraceStream = stackTraceStream.filter { s -> s.contains(filter) }
        }

        if (limit != null) {
            stackTraceStream = stackTraceStream.limit(limit.toLong())
        }

        val stackTraceElements = stackTraceStream.collect(Collectors.toList())
        stackTraceElements.add(0, fmt.format(Date()) + " -> " + t.localizedMessage)

        val maxLength = stackTraceElements.stream()
            .max(Comparator.comparingInt(String::length))
            .map(String::length)
            .get()

        sb.append(color.value).append(Border.LEFT_UP.value)
        for (i in 0 until maxLength + 2) {
            sb.append(Border.HORIZONTAL.value)
        }
        sb.append(Border.RIGHT_UP.value).append(Color.ANSI_RESET.value).append("\n")

        stackTraceElements.forEach { element ->
            sb.append(color.value).append(Border.VERTICAL.value).append(" ").append(element)
            for (i in element.length..maxLength) {
                sb.append(" ")
            }
            sb.append(Border.VERTICAL.value).append(Color.ANSI_RESET.value).append("\n")
        }

        sb.append(color.value).append(Border.LEFT_BOTTOM.value)
        for (i in 0 until maxLength + 2) {
            sb.append(Border.HORIZONTAL.value)
        }
        sb.append(Border.RIGHT_BOTTOM.value).append(Color.ANSI_RESET.value)
        return sb.toString()
    }

    fun print(t: Throwable, color: Color = Color.ANSI_RED_BACKGROUND, filter: String? = "com.linweiyuan", limit: Int? = null) {
        println(get(t, color, filter, limit))
    }
}
