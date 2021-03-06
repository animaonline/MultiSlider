package io.apptik.widget.example.multislider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiCollection;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiSelector;
import io.apptik.widget.MultiSlider;
import io.apptik.widget.UiMultiSlider;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static io.apptik.widget.MultiSliderActions.moveThumbBackward;
import static io.apptik.widget.MultiSliderActions.moveThumbForward;
import static io.apptik.widget.MultiSliderActions.setThumbValue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class ExampleTest {

    @Rule
    public ActivityTestRule<MyActivity> mActivityRule = new ActivityTestRule<>(
            MyActivity.class);

    private UiDevice mDevice;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void moveSingle() throws Exception {
        UiMultiSlider slider = new UiMultiSlider(new UiCollection(new UiSelector()
                .className(MultiSlider.class)
                .resourceIdMatches(".*multiSlider2.*"))
                .getChild(new UiSelector().textStartsWith("thumb 0:")));

        for (int i = 0; i < 15; i++) {
            slider.moveThumbForward();
        }
        slider.setThumbValue(10);
        for (int i = 0; i < 10; i++) {
            slider.moveThumbBackward();
        }
        slider.moveThumbBackward();

        for (int i = 0; i < 90; i++) {
            onView(ViewMatchers.withId(R.id.multiSlider3))
                    .perform(moveThumbForward(0));
        }
        onView(ViewMatchers.withId(R.id.multiSlider3))
                .perform(setThumbValue(0, 50));
        for (int i = 0; i < 15; i++) {
            onView(ViewMatchers.withId(R.id.multiSlider3))
                    .perform(moveThumbBackward(0));
        }
        onView(ViewMatchers.withId(R.id.multiSlider3))
                .perform(click());
    }
}
