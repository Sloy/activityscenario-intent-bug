# ActivityScenario's Intent Bug
Sample project to reproduce a bug with [ActivityScenario](https://developer.android.com/reference/androidx/test/core/app/ActivityScenario) when using Intents with actions.

The relevant code is in the instrumentation test named [MainActivityTest](https://github.com/Sloy/activityscenario-intent-bug/blob/master/app/src/androidTest/java/com/sloydev/activityscenariobugdemo/MainActivityTest.kt)
```kotlin
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
```

Here's the result from the execution:
![result screenshot](result-screenshot.png)