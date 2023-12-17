package com.slothdeboss.espressopracticetemplate.ui.util

import java.util.concurrent.atomic.AtomicBoolean

class Event<out T>(private val content: T) {

    private val beenHandled = AtomicBoolean(false)

    fun getContentIfNotHandled(): T? {
        return if (!beenHandled.get()) {
            beenHandled.set(true)
            content
        } else {
            null
        }
    }

    fun peekContent(): T {
        return content
    }
}