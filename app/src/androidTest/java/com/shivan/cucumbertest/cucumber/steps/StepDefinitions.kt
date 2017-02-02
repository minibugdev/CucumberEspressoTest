package com.shivan.cucumbertest.cucumber.steps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.shivan.cucumbertest.MainActivity
import com.shivan.cucumbertest.R
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * This defines all the translations from Gherkin (semi-English) sentences to Java
 */
@SuppressWarnings("JUnitTestCaseWithNoTests")
@RunWith(AndroidJUnit4::class)
class StepDefinitions {
	private var mInstrumentationContext: Context? = null
	private var mAppContext: Context? = null
	private var mActivity: Activity? = null

	@Rule
	private val mActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

	@Before
	@Throws(Exception::class)
	fun setUp() {
		mInstrumentationContext = InstrumentationRegistry.getContext()
		mAppContext = InstrumentationRegistry.getTargetContext()
		mActivity = mActivityRule.launchActivity(Intent()) // Start Activity before each test scenario
	}

	/**
	 * All the clean up of application's data and state after each scenario must happen here
	 * The last call of this method should always be the call to parent's tear down method
	 */
	@After
	@Throws(Exception::class)
	fun tearDown() {
		mActivity?.finish()
	}

	@Given("^แสดงหน้าหลัก$")
	fun focus_on_the_main_screen() {
		assertNotNull(mActivity)
	}

	@When("^เมื่อพิมพ์ \"(.+)\"$")
	fun enter_the_data(data: String) {
		onView(withId(R.id.input)).perform(typeText(data), closeSoftKeyboard())
	}

	@When("^ไม่กรอกข้อมูล$")
	fun clear_the_data() {
		onView(withId(R.id.input)).perform(clearText(), closeSoftKeyboard())
	}

	@And("^กดปุ่ม \"(.+)\"$")
	fun tap_on_button(buttonAliasOrText: String) {
		when (buttonAliasOrText) {
			"FAB" -> onView(withId(R.id.fab)).perform(click())
			else  -> onView(withText(buttonAliasOrText)).perform(click())
		}
	}

	@Then("^แสดงข้อความ \"(.+)\"$")
	fun i_see_the_message(message: String) {
		onView(withText(message)).check(matches(isDisplayed()))
	}
}