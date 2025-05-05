package com.alancamargo.weapons.core.test.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

fun assertTextIsDisplayed(text: String) {
    onView(withText(text)).check(matches(isDisplayed()))
}
