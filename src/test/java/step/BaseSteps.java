package step;

import base.BaseTest;
import model.ElementInfo;
import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class BaseSteps extends BaseTest {

    public static int DEFAULT_MAX_ITERATION_COUNT = 1;
    public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;

    private static String SAVED_ATTRIBUTE;

    private String compareText;
    private Map<String, String> memoryMap = new HashMap<>();

    public BaseSteps() {
        initMap(getFileList());
    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoToBy(findElementInfoByKey(key)));
    }

    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }

    public boolean isElementVisible(String key, long timeOut) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(infoParam));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public WebElement findElementWithKey(String key) {
        return findElement(key);
    }

    public String getElementText(String key) {
        return findElement(key).getText();
    }


    @Step({"Wait <value> seconds",
            "<int> saniye bekle"})
    public void waitBySeconds(int seconds) {
        try {
            logger.info(seconds + " saniye bekleniyor.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Step({"Wait <value> milliseconds",
            "<long> milisaniye bekle"})
    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info(milliseconds + " milisaniye bekleniyor.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step({"Wait for element then click <key>",
            "Elementi bekle ve sonra tıkla <key>"})
    public void checkElementExistsThenClick(String key) {
        getElementWithKeyIfExists(key);
        waitForElementClickable(key);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", findElement(key));
        logger.info(key + "elementine tıklanıldı");
    }

    @Step({"Check if element <key> exists",
            "Element var mı kontrol et <key>"})
    public WebElement getElementWithKeyIfExists(String key) {
        WebElement webElement;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            try {
                webElement = findElementWithKey(key);
                logger.info(key + " elementi bulundu.");
                return webElement;
            } catch (WebDriverException e) {
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        assertFalse(Boolean.parseBoolean("Element: '" + key + "' doesn't exist."));
        logger.info(key + " elementi yok.");
        return null;
    }
    @Step({"Wait element <key> clickable",
            "Element tıklanabilir oluncaya kadar bekle <key>"})
    public WebElement waitForElementClickable(String key) {
        WebElement webElement;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            try {
                webElement = findElementWithKey(key);


                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.elementToBeClickable(webElement));

                logger.info(key + " elementi tıklanabilir durumda.");
                return webElement;
            } catch (WebDriverException e) {

            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }


        assertFalse(Boolean.parseBoolean("Element: '" + key + "' doesn't exist."));
        logger.info(key + " elementi yok.");
        return null;


    }
    @Step({"Go to <url> address",
            "<url> adresine git"})
    public void goToUrl(String url) {
        driver.get(url);
        logger.info(url + " adresine gidiliyor.");
    }
    @Step("Elemente TAB keyi ve <text>,<text2>,<text3>,<text4> yolla <key>")
    public void sendKeyToElementTAB(String text,String text2,String text3,String text4,String key) {
        findElement(key).sendKeys(Keys.TAB+text+Keys.TAB+text2+Keys.TAB+text3+Keys.TAB+text4+Keys.TAB+Keys.ENTER);
        logger.info(key + " elementine TAB keyi yollandı.");
    }
    @Step({"Check if <key> element's attribute <attribute> contains the value <expectedValue>",
            "<key> elementinin <attribute> niteliği <value> değerini içeriyor mu"})
    public void checkElementAttributeContains(String key, String attribute, String expectedValue) {
        WebElement element = findElement(key);

        String actualValue;
        int loopCount = 0;
        while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
            actualValue = element.getAttribute(attribute).trim();
            if (actualValue.contains(expectedValue)) {
                return;
            }
            loopCount++;
            waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
        }
        Assertions.fail("Element's attribute value doesn't contain expected value");
    }

    @Step({"Check if element <key> contains text <expectedText>",
            "<key> elementi <expectedText> değerini içeriyor mu kontrol et"})
    public void checkElementContainsText(String key, String expectedText) throws InterruptedException {

        try {

            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".toast-message")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            List<String> toastMessageText = (List<String>) js.executeScript(
                    "return Array.from(document.querySelectorAll('.toast-message')).map(el => el.innerText);"
            );

            Boolean containsText = toastMessageText.stream().anyMatch(msg -> msg.contains(expectedText));
            assertTrue(containsText, "Beklenen bildirim mesajı " + "'( " + expectedText + " olması gerekirken ," + ")'" +
                    " Gerçekleşen bildirim mesajı  " + "( " + toastMessageText + " )'");
            logger.info("Beklenen bildirim mesajı görüntülendi: " + toastMessageText);
        } catch (TimeoutException e) {

            logger.warn("Bildirim mesajı bulunamadı veya beklenen sürede yakalanamadı.");
        }
    }

    @Step("<key> elementi  beklenen  <expectedText> değerini içeriyor mu kontrol et")
    public void checkElementContainsTextt(String key, String expectedText) throws InterruptedException {

        getElementWithKeyIfExists(key);
        String actualText = findElement(key).getText();
        assertTrue(expectedText.contains(actualText));
        assertEquals(expectedText, actualText, "beklenen mesaj = " + "'" + expectedText + "'  olması gerekirken " + " --Gerçekleşen =" + actualText);


    }

    @Step("<key> elementi <text> değerini içermiyor mu kontrol et")
    public void checkElementNotContainsText(String key, String expectedText) {
        getElementWithKeyIfExists(key);
        Boolean containsText = findElement(key).getText().contains(expectedText);
        System.out.println("containsText = " + containsText);
        System.out.println("expectedText = " + expectedText);
        assertFalse(containsText, "Beklenen degeri iceriyor " + findElement(key).getText());
        logger.info(key + " elementi " + expectedText + " değerini içermiyor.");
    }




    private JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }






    @Step({"<key> li elementi bul, temizle ve <text> değerini yaz",
            "Find element by <key> clear and send keys <text>"})
    public void sendKeysByKey(String key, String text) {

        WebElement webElement = findElementWithKey(key);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.clear();
        webElement.sendKeys(text);
        logger.info(key + " alanina " + text + " degeri yazildi ");
    }
    @Step("Alert Var mı Kontrol Et")
    public boolean isAlertVisible() {

        try {

            Alert alert = driver.switchTo().alert();

            return true;
        } catch (NoAlertPresentException e) {

            return false;
        }
    }


    @Step("<key> olarak comboboxtan yazılı <text> seçimini yap")
    public void selectDropDownText(String key, String text) {
        Select select = new Select(findElement(key));
        select.selectByVisibleText(text);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }





    @Step("<key> elementinin text bilgisini al ve <varName> ile kaydet")
    public void getTextElementAndSave(String key, String varName) {
        if (isElementVisible(key, 10)) {
            String elementText = getElementText(key);
            // Elementin metnini değişken adıyla hafızaya kaydet
            memoryMap.put(varName, elementText); // 'varName' anahtarıyla kaydet
            logger.info(key + " elementinin text içeriği: " + elementText +
                    "\n'" + elementText + "' bilgisi '" + varName + "' adıyla hafızaya kaydedildi!");
        } else {
            Assertions.fail(key + " elementi görünür olmadı!");
        }
    }

    @Step("<keyA> değerinden <keyB> değerini çıkardığında <expectedResult> sonucunu vermeli")
    public void subtractAndVerify(String keyA, String keyB, int expectedResult) {

        System.out.println("ilkParçaAdedi: " + memoryMap.get(keyA));
        System.out.println("ikinciParçaAdedi: " + memoryMap.get(keyB));


        String valueAString = memoryMap.get(keyA);
        String valueBString = memoryMap.get(keyB);


        if (valueAString == null) {
            Assertions.fail(keyA + " için hafızada değer bulunamadı!");
        }
        if (valueBString == null) {
            Assertions.fail(keyB + " için hafızada değer bulunamadı!");
        }


        int valueA = parseFormattedNumber(valueAString);
        int valueB = parseFormattedNumber(valueBString);


        int result = valueA - valueB;


        assertEquals(expectedResult, result, "Cıkarma islemi sonucu beklenenden farkli!  Gerceklesen Sonuc =  "+result);
        logger.info("Çıkarma işlemi başarılı! " + valueA + " - " + valueB + " = " + result);
    }
    private int parseFormattedNumber(String number) {
        try {
            number = number.replace(",", "");
            if (number.contains(".")) {
                number = number.substring(0, number.indexOf('.'));
            }
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Geçersiz sayı formatı: " + number, e);
        }
    }







}

