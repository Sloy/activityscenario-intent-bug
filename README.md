# ActivityScenario's Intent Bug
Sample project to reproduce a bug with [ActivityScenario](https://developer.android.com/reference/androidx/test/core/app/ActivityScenario) when using Intents with actions.

## Code
The relevant code is in the instrumentation test named [MainActivityTest](https://github.com/Sloy/activityscenario-intent-bug/blob/master/app/src/androidTest/java/com/sloydev/activityscenariobugdemo/MainActivityTest.kt):
```kotlin
@Test
fun intent_with_action() {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("scenario://test"))

    ActivityScenario.launch<MainActivity>(intent)
    // Fails: java.lang.AssertionError: Activity never becomes requested state "[CREATED, STARTED, RESUMED, DESTROYED]" (last lifecycle transition = "PRE_ON_CREATE")
}
```

## Result:
![result screenshot](result-screenshot.png)

Full stacktrace:
```
java.lang.AssertionError: Activity never becomes requested state "[CREATED, STARTED, RESUMED, DESTROYED]" (last lifecycle transition = "PRE_ON_CREATE")
at androidx.test.core.app.ActivityScenario.waitForActivityToBecomeAnyOf(ActivityScenario.java:301)
at androidx.test.core.app.ActivityScenario.launchInternal(ActivityScenario.java:235)
at androidx.test.core.app.ActivityScenario.launch(ActivityScenario.java:209)
at com.sloydev.activityscenariobugdemo.MainActivityTest.intent_with_action(MainActivityTest.kt:15)
at java.lang.reflect.Method.invoke(Native Method)
at java.lang.reflect.Method.invoke(Method.java:372)
at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runners.Suite.runChild(Suite.java:128)
at org.junit.runners.Suite.runChild(Suite.java:27)
at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
at androidx.test.internal.runner.TestExecutor.execute(TestExecutor.java:56)
at androidx.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:388)
at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:1853)
```
