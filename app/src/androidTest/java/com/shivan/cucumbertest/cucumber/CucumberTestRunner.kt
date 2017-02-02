package com.shivan.cucumbertest.cucumber

import android.os.Bundle
import android.support.test.runner.AndroidJUnitRunner

import cucumber.api.android.CucumberInstrumentationCore

/**
 * Used in build.gradle/testInstrumentationRunner to run Cucumber tests
 * 'testInstrumentationRunner' variable in build.gradle has to point to this package
 * This class must be in a different package than the glue code
 * (this class is in '...cucumber' and glue is in '...cucumber.steps')
 */
class CucumberTestRunner : AndroidJUnitRunner() {
    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(bundle: Bundle) {
        super.onCreate(bundle)
        instrumentationCore.create(bundle)
    }

    override fun onStart() {
        waitForIdleSync()
        instrumentationCore.start()
    }
}
