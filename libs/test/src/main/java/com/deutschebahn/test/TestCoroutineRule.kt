package com.deutschebahn.test

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineRule : TestRule {

    val dispatcher = TestCoroutineDispatcher()
    val scope = TestCoroutineScope(dispatcher)

    fun before(description: Description) {
        Dispatchers.setMain(dispatcher)
    }

    fun after(description: Description) {
        Dispatchers.resetMain()
        scope.cleanupTestCoroutines()
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        dispatcher.runBlockingTest(block)

    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            override fun evaluate() {
                before(description)

                try {
                    base.evaluate()
                } finally {
                    after(description)
                }
            }
        }
}
