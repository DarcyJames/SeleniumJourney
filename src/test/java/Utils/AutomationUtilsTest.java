package Utils;

import com.automation.utils.BrowserUtils;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutomationUtilsTest {

    private final static int CHROME = 0;
    private final static int FIREFOX = 1;

    @Test
    public void testGetBrowserDriver() {
        BrowserUtils mockBrowserUtils = mock(BrowserUtils.class);
        WebDriver mockDriver = mock(WebDriver.class);
        when(mockBrowserUtils.getBrowserDriver(CHROME)).thenReturn(mockDriver);
        when(mockBrowserUtils.getBrowserDriver(FIREFOX)).thenReturn(mockDriver);
    }

}

