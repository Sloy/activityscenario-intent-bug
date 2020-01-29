package com.sloydev.activityscenariobugdemo

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import org.junit.Test

class MainActivityTest {
    @Test
    fun intent_with_action() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("scenario://test"))

        ActivityScenario.launch<MainActivity>(intent)
        // Fails: java.lang.AssertionError: Activity never becomes requested state "[CREATED, STARTED, RESUMED, DESTROYED]" (last lifecycle transition = "PRE_ON_CREATE")
    }

    @Test
    fun intent_with_action_and_component_name() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("scenario://test"))
        intent.component = ComponentName(BuildConfig.APPLICATION_ID, MainActivity::class.java.name)

        ActivityScenario.launch<MainActivity>(intent)
    }

    @Test
    fun intent_with_action_and_class() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)

        ActivityScenario.launch<MainActivity>(intent)
    }
}
