package com.automation.shopping;

import com.automation.utils.AutomationUtils;
import com.automation.utils.BrowserUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingTest {

    //utils
    BrowserUtils browserUtils;
    AutomationUtils automationUtils;
    private static WebDriver webDriver;
    private final static int CHROME = 0;
    private final static int FIREFOX = 1;
    public final String[] classNamesKnownPopups = {"at-close-icon", "survey-no btn", "ui-modal-header", "survey-no btn"};
    private static final String URL = "https://www.bestbuy.ca/";
    private static final String URL_2 = "https://www.long-mcquade.com/";
    private static final String URL_3 = "https://dashcamtalk.com/best-dash-cams-of-2018/";
    private static final String URL_4 = "https://www.ca.thinkware.com/f800pro";
    private static final String product_1 = "air conditioner";
    private static final String product_2 = "Headphones";
    private static final String product_3 = "Treadmill";
    private static final String product_4 = "Troublemaker blue";
    //Elements
    private WebElement bestBuySearchField;
    private WebElement bestBuySearchButton;
    private WebElement musicStoreSearchField;
    private WebElement musicStoreSearchButton;
    private WebElement musicStoreAddToCartButton;
    private WebElement musicStoreViewCartLink;
    private WebElement dashcamDropdown;
    private WebElement dashcamItemInDropdown;
    private WebElement dashcamImageThumb;
    private WebElement dashcamRightScrollArrow;
    private WebElement dashcamProductGallery;
    private WebElement dashcamImageLink;
    private WebElement dashcamProductsLink;

    @BeforeClass
    public static void initializeWebDrivers() {
        webDriver = null;
    }

    public ShoppingTest() {
        browserUtils = new BrowserUtils();
        automationUtils = new AutomationUtils();
    }

    /**
     * Get the appropriate WebDriver from the AutomationUtils
     * as per the browser type provided.
     *
     * @param browser
     * @return
     */
    public boolean configureBrowser(int browser) {
        webDriver = browserUtils.getBrowserDriver(browser);
        webDriver.manage().window().maximize();
        //intermediary between a client object and a target object.
//        MyPageFactory.initElements(webDriver, this);
        return webDriver != null;
    }

    @Test
    /**
     * Shop for several items using Chrome as the browser
     */
    public void shopChrome() {
        if (configureBrowser(this.CHROME)) {
            shopAtBestBuy(webDriver, URL, product_1, product_2, product_3);
            shopAtMusicStore(webDriver, URL_2, product_4);
            //researchAndBrowseDashcam(webDriver, URL_3, URL_4);
            automationUtils.waitSeconds(2);
        }
        browserUtils.killBrowserKillDriver(webDriver);
    }

    //    @Test
    /**
     * Shop for several items using Firefox as the browser
     */
//    public void shopFFox() {
//        if (configureBrowser(this.FIREFOX)) {
//            shopAtBestBuy(webDriver, URL, product_1, product_2, product_3);
//            shopAtMusicStore(webDriver, URL_2, product_4);
//            researchAndBrowseDashcam(webDriver, URL_3, URL_4);
//            AutomationUtils.waitSeconds(2);
//        }
//        AutomationUtils.killBrowser(webDriver);
//    }

    /**
     * Using the WebDriver provided, go to the URL, and shop for products
     * at BestBuy, killing popups along the way.
     *
     * @param webDriver
     * @param url
     * @param products
     */
    private void shopAtBestBuy(WebDriver webDriver, String url, String... products) {
        webDriver.get(url);
        for (String product : products) {
            bestBuySearchField = webDriver.findElement(By.id("ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_TxtSearchKeyword"));
            automationUtils.killThisPopupIfItExists(webDriver, classNamesKnownPopups);
            bestBuySearchField.sendKeys(product);
            bestBuySearchButton = webDriver.findElement(By.className("icon-search"));
            automationUtils.killThisPopupIfItExists(webDriver, classNamesKnownPopups);
            bestBuySearchButton.click();
            automationUtils.waitSeconds(2);
        }
    }

    /**
     * Using the WebDriver provided, go to the URL, and shop for products at the music
     * store.
     *
     * @param webDriver
     * @param url
     * @param product
     */
    public void shopAtMusicStore(WebDriver webDriver, String url, String product) {
        //Get product 4
        webDriver.get(url);
        musicStoreSearchField = webDriver.findElement(By.id("SearchTxt"));
        musicStoreSearchField.sendKeys(product);
        musicStoreSearchButton = webDriver.findElement(By.id("SearchBtn"));
        musicStoreSearchButton.click();
        musicStoreAddToCartButton = webDriver.findElement(By.id("add-to-cart-btn"));
        musicStoreAddToCartButton.click();
        musicStoreViewCartLink = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div[2]/span[2]/a"));
        musicStoreViewCartLink.click();
        automationUtils.waitSeconds(2);
    }

    /**
     * Using the provided WebDriver, go to a URL, find a product, click on its picture,
     * then go to another URL and do the same.
     *
     * @param webDriver
     * @param url1
     * @param url2
     */
    public void researchAndBrowseDashcam(WebDriver webDriver, String url1, String url2) {
        //Get product 5
        webDriver.get(url1);
        dashcamDropdown = webDriver.findElement(By.id("page_id"));
        dashcamDropdown.click();

        dashcamItemInDropdown = webDriver.findElement(By.xpath("//*[@id=\"page_id\"]/option[86]"));
        dashcamItemInDropdown.click();

        automationUtils.waitSeconds(2);

        webDriver.get(url2);

        automationUtils.waitSeconds(2);

        dashcamProductsLink = webDriver.findElement(By.xpath(
                "//*[@id=\"div_menu\"]/div/div/div[1]/div"));
        dashcamProductsLink.click();

        automationUtils.waitSeconds(1);

        dashcamImageLink = webDriver.findElement(By.xpath(
                "//*[@id=\"div_top_subpanel_inner1\"]/div/div/div[1]/div[2]/div/div[1]/div/div[1]/img"));
        dashcamImageLink.click();

        dashcamProductGallery = webDriver.findElement(By.xpath("//*[@id=\"p_top\"]/div/div[1]/div[12]/div/a"));
        dashcamProductGallery.click();

        automationUtils.waitSeconds(1);
        dashcamRightScrollArrow = webDriver.findElement(By.xpath("//*[@id=\"fs_Gallery\"]/div/div/div/div[1]/div[3]/img"));
        dashcamRightScrollArrow.click();
        automationUtils.waitSeconds(1);
        dashcamImageThumb = webDriver.findElement(By.xpath("//*[@id=\"imgThumb10\"]"));
        dashcamImageThumb.click();
    }

    @AfterClass
    public static void killBrowser() {
        if (null != webDriver) {
            BrowserUtils.killBrowserKillDriver(webDriver);
            webDriver = null;
        }
    }
}
