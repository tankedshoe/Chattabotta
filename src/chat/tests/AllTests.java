package chat.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Sets up the tests.
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
@RunWith(Suite.class)
@SuiteClasses({ ChatbotTest.class, ControllerTest.class, FrameTest.class, MovieTest.class, PanelTest.class })
public class AllTests
{

}
