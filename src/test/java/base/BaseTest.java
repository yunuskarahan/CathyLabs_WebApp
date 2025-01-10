package base;


import com.google.gson.Gson;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.ExecutionContext;
import model.ElementInfo;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseTest {

    protected static WebDriver driver;
    protected static Actions actions;
    protected Logger logger = LoggerFactory.getLogger(getClass());
    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;
    EdgeOptions edgeOptions;

    String browserName = "chrome";
    String selectPlatform = "win";

    String testURL = "https://catchylabs-webclient.testinium.com/";

    public static final String DEFAULT_DIRECTORY_PATH = "elementValues";
    ConcurrentMap<String, Object> elementMapList = new ConcurrentHashMap<>();

    @BeforeScenario
    public void setUp() {
        clearScreenshots();
        logger.info("************************************  BeforeScenario  ************************************");
        try {
            if (StringUtils.isEmpty(System.getenv("key"))) {
                logger.info("Local cihazda " + selectPlatform + " ortamında " + browserName + " browserında test ayağa kalkacak");
                if ("win".equalsIgnoreCase(selectPlatform)) {
                    if ("chrome".equalsIgnoreCase(browserName)) {
                        driver = new ChromeDriver(chromeOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                        driver.get(testURL);
                    } else if ("firefox".equalsIgnoreCase(browserName)) {
                        driver = new FirefoxDriver(firefoxOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                        driver.get(testURL);
                    } else if ("edge".equalsIgnoreCase(browserName)) {
                        driver = new EdgeDriver(edgeOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                        driver.get(testURL);
                    }
                } else if ("windows".equalsIgnoreCase(selectPlatform)) {
                    if ("chrome".equalsIgnoreCase(browserName)) {
                        driver = new ChromeDriver(chromeOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    } else if ("firefox".equalsIgnoreCase(browserName)) {
                        driver = new FirefoxDriver(firefoxOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    } else if ("edge".equalsIgnoreCase(selectPlatform)) {
                        driver = new EdgeDriver(edgeOptions());
                        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                    }
                    actions = new Actions(driver);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterScenario
    public void tearDown(ExecutionContext context) {
        if (context.getCurrentScenario().getIsFailing()) {
            takeScreenshot();
        }

        driver.quit();

    }

    public void initMap(File[] fileList) {
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList = null;
        for (File file : fileList) {
            try {
                elementInfoList = gson
                        .fromJson(new FileReader(file), elementType);
                elementInfoList.parallelStream()
                        .forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
            } catch (FileNotFoundException e) {
                logger.warn("{} not found", e);
            }
        }
    }

    public File[] getFileList() {
        File[] fileList = new File("src/test/resources/" + DEFAULT_DIRECTORY_PATH).listFiles(pathname -> !pathname.isDirectory() && pathname.getName().endsWith(".json"));
        if (fileList == null) {
            logger.warn(
                    "File Directory Is Not Found! Please Check Directory Location. Default Directory Path = {}",
                    DEFAULT_DIRECTORY_PATH);
            throw new NullPointerException();
        }
        return fileList;
    }


    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        //chromeOptions.addArguments("--ignore-certificate-errors");
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver.exe");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }


    public EdgeOptions edgeOptions() {
        edgeOptions = new EdgeOptions();
        Map<String, Object> map = new HashMap<>();
        List<String> args = Arrays.asList("--start-fullscreen", "--disable-blink-features=AutomationControlled"
                //,"-inprivate"
                , "--ignore-certificate-errors");
        map.put("args", args);
        edgeOptions.setCapability("ms:edgeOptions", map);
        System.setProperty("webdriver.edge.driver", "web_driver/msedgedriver.exe");
        edgeOptions.merge(capabilities);
        edgeOptions.setCapability("--start-fullscreen", true);
        return edgeOptions;
    }


    public FirefoxOptions firefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        firefoxOptions.addArguments("--kiosk");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--start-fullscreen");
        FirefoxProfile profile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability("marionette", true);
        firefoxOptions.merge(capabilities);
        System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver");
        return firefoxOptions;
    }

    public ElementInfo findElementInfoByKey(String key) {
        return (ElementInfo) elementMapList.get(key);
    }

    public void saveValue(String key, String value) {
        elementMapList.put(key, value);
    }

    public String getValue(String key) {
        Object value = elementMapList.get(key);
        if (value == null) {
            logger.error("Hafızada '" + key + "' anahtarı ile ilişkili bir değer bulunamadı!");
            return null; // null döndür
        }
        return elementMapList.get(key).toString();
    }

    public void takeScreenshot() {
        try {
            String directoryPath = "screenshots";
            File screenshotDir = new File(directoryPath);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            if (driver instanceof TakesScreenshot) {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);

                String screenshotPath = directoryPath + "/screenshot_" + System.currentTimeMillis() + ".png";
                Files.write(Paths.get(screenshotPath), screenshotBytes);
                logger.info("Screenshot saved at: " + screenshotPath);
            }
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
        }
    }

    public void clearScreenshots() {
        try {
            String directoryPath = "screenshots";
            File screenshotDir = new File(directoryPath);


            if (screenshotDir.exists()) {
                for (File file : screenshotDir.listFiles()) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
                logger.info("Old screenshots cleared.");
            }
        } catch (Exception e) {
            logger.error("Failed to clear screenshots", e);
        }
    }





}