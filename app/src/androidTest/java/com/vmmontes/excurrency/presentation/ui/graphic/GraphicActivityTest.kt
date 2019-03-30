package com.vmmontes.excurrency.presentation.ui.graphic


import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.vmmontes.excurrency.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GraphicActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(GraphicActivity::class.java)

    @Test
    fun graphicActivityTestRotation() {
        val relativeLayout = onView(
            allOf(
                withId(R.id.layoutStartDate),
                childAtPosition(
                    allOf(
                        withId(R.id.layoutDate),
                        childAtPosition(
                            withClassName(`is`("android.support.constraint.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        relativeLayout.perform(click())

        val appCompatTextView = onView(
            allOf(
                withClassName(`is`("android.support.v7.widget.AppCompatTextView")), withText("2019"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatTextView2 = onData(anything())
            .inAdapterView(
                allOf(
                    withClassName(`is`("android.widget.YearPickerView")),
                    childAtPosition(
                        withClassName(`is`("com.android.internal.widget.DialogViewAnimator")),
                        1
                    )
                )
            )
            .atPosition(118)
        appCompatTextView2.perform(scrollTo(), click())

        val appCompatButton = onView(
            allOf(
                withId(android.R.id.button1), withText("Aceptar"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())

        val relativeLayout2 = onView(
            allOf(
                withId(R.id.layoutEndDate),
                childAtPosition(
                    allOf(
                        withId(R.id.layoutDate),
                        childAtPosition(
                            withClassName(`is`("android.support.constraint.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        relativeLayout2.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("Aceptar"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.buttonSearch), withText("Buscar"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_frame),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val relativeLayout3 = onView(
            allOf(
                withId(R.id.layoutStartDate),
                childAtPosition(
                    allOf(
                        withId(R.id.layoutDate),
                        childAtPosition(
                            withClassName(`is`("android.support.constraint.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        relativeLayout3.perform(click())

        val appCompatButton4 = onView(
            allOf(
                withId(android.R.id.button2), withText("Cancelar"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatButton4.perform(scrollTo(), click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.buttonSearch), withText("Buscar"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_frame),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.buttonSearch), withText("Buscar"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.view_frame),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton6.perform(click())
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
