package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtils {

    private static final String pathToChromeDriver = "C:\\dev\\libs\\selen_browser_drivers\\chromedriver.exe";
    private static final String pathToFFoxDriver = "C:\\dev\\libs\\selen_browser_drivers\\geckodriver.exe";
    private final static int CHROME = 0;
    private final static int FIREFOX = 1;

    /**
     * Returns appropriate browser driver according to the browser type provided.
     *
     * @param browser
     * @return
     */
    public WebDriver getBrowserDriver(int browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
                return new ChromeDriver();
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", pathToFFoxDriver);
                return new FirefoxDriver();
            default:
                return null;
        }
    }

    /**
     * Closes the browser and *ATTEMPTS* to kill the Windows process.
     * Closing the browser doesn't necessarily kill the process.
     *
     * @param webDriver
     */
    public static void killBrowserKillDriver(WebDriver webDriver) {
        if (webDriver != null) {
            try {
                webDriver.close();
                if (webDriver.getClass().getName().contains("ChromeDriver")) {
                    Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                }
                if (webDriver.getClass().getName().contains("FirefoxDriver")) {
                    Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
                }
                webDriver = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
