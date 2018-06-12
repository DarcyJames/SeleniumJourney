package Shopping;

import com.automation.shopping.ShoppingTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingTestTest {

    private final static int CHROME = 0;
    private final static int FIREFOX = 1;

    @Test
    public void testGetBrowser() {
        ShoppingTest mockShoppingTest = mock(ShoppingTest.class);
        WebDriver mockDriver = mock(WebDriver.class);
        when(mockShoppingTest.configureBrowser(CHROME)).thenReturn(true);
    }

}
