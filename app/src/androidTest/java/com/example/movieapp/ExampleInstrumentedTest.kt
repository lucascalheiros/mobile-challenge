package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.movieapp.view.initialScreen.InitialScreenFragment
import com.example.movieapp.view.nowPlaying.NowPlayingFragment
import kotlinx.android.synthetic.main.fragment_initial_screen.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {
    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<InitialScreenFragment>()
        scenario.onFragment(FragmentScenario.FragmentAction {
            onView(withId(R.id.to_playing_btn)).perform(click())
        })
//        intended(hasComponent("com.example.movieapp.view.nowPlaying.NowPlayingFragment"))

    }
//        onView(withId(R.id.to_playing_btn))
//            .perform(click())


//    @get:Rule
//    val activityTestRule = ActivityTestRule(
//        MainActivity::class.java, false, true)
//
//
//
//
//    @Test
//    fun useAppContext() {
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.movieapp", appContext.packageName)
//
//
////        onView(withId(R.id.to_playing_btn)).perform(click())
////        intended(hasComponent("com.example.movieapp.view.nowPlaying.NowPlayingFragment"))
//
//
////        Espresso.onView(ViewMatchers.withId(R.id.now_playing_recycler))
////            .perform(RecyclerViewActions.actionOnItemAtPosition<MoviesListAdapter.ViewHolder>(0, click()))
//    }
}
