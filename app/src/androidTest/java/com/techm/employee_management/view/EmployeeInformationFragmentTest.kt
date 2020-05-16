package com.techm.employee_management.view

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.techm.employee_management.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class EmployeeInformationFragmentTest
{
    /*@get: Rule
    val activityRule:ActivityScenarioRule<ContainerActivity> = ActivityScenarioRule(ContainerActivity::class.java)*/

    @Test
    fun test_isActivityInView() {
        val activityScenario=ActivityScenario.launch(ContainerActivity::class.java)
        Espresso.onView(withId(R.id.layout_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isSearchDisplayed()
    {
        val activityScenario=ActivityScenario.launch(ContainerActivity::class.java)
        val linearLayout = onView(
            Matchers.allOf(
                withId(R.id.search_bar),
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.searchView),
                        childAtPosition(
                            IsInstanceOf.instanceOf(RelativeLayout::class.java),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))



    }
    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}