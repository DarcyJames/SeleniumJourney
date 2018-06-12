package com.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class AutomationUtils {


    /**
     * A basic threaded delay.
     *
     * @param secs
     */
    public void waitSeconds(long secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * A list of known popup class names is provided, and if any of these are found,
     * the driver will * ATTEMPT * to close each one in the list.
     *
     * @param webDriver
     * @param className
     */
    public void killThisPopupIfItExists(WebDriver webDriver, final String... className) {
        for (String nameOfClass : className) {
            try {
                final WebElement closeIcon = webDriver.findElement(By.className(nameOfClass));
                if (null != closeIcon) {
                    System.out.println("<<<<<<< TestUtils.killThisPopupIfItExists(): " + nameOfClass + ">>>>>>>");
                    closeIcon.click();
                }
                //If Foresee
                final WebElement foreseeNoThanks = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[1]/a[2]"));
                if (null != foreseeNoThanks) {
                    System.out.println(">>> Saying no thanks to Foresee >>>");
                    foreseeNoThanks.click();
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
//                System.out.println("No popup found: " + e);
            } catch (Exception ge) {
//                System.out.println("Exception in killThisPopup: " + ge);
            }
        }
    }

    /**
     * This is deprecated at worst, ignored at best. It's supposed to work to close any window other
     * than the currently open one.
     *
     * @param webDriver
     * @param mainWindowHandle
     */
    public void closeAllOtherWindows(WebDriver webDriver, String mainWindowHandle) {
        Set<String> allWindowHandles = webDriver.getWindowHandles();
        for (String currentWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equals(mainWindowHandle)) {
                System.out.println("<<<< FOUND POPUP and closing it: " + currentWindowHandle + ">>>>");
                webDriver.switchTo().window(currentWindowHandle);
                webDriver.close();
            }
        }
        //webDriver.switchTo().window(mainWindowHandle);
        //return webDriver.getWindowHandles().size();
    }
}
