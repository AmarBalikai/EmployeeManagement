package com.techm.employee_management.view

import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.techm.employee_management.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AddEmployeeFragmentTest{


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ContainerActivity::class.java)

    @Test
    fun test_isButtonTitleDisplayed()
    {
        val textView3 = Espresso.onView(
            Matchers.allOf(
                withId(R.id.action_add), ViewMatchers.withText("ADD"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        1
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView3.check(ViewAssertions.matches(ViewMatchers.withText("ADD")))
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

    @Test
    fun isAddButtonClick()
    {
        val activityScenario=ActivityScenario.launch(ContainerActivity::class.java)
        val actionMenuItemView = Espresso.onView(
            Matchers.allOf(
                withId(R.id.action_add), ViewMatchers.withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar),
                        1
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        actionMenuItemView.perform(ViewActions.click())

    }
}