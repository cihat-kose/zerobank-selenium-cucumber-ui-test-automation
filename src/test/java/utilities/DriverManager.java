package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Locale;

public class DriverManager {
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static ThreadLocal<String> threadBrowserName = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (threadBrowserName.get() == null) {
            setThreadBrowserName(System.getProperty("browser", "edge"));
        }

        if (threadDriver.get() == null) {
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            switch (threadBrowserName.get()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("-headless");
                    threadDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) chromeOptions.addArguments("--headless=new");
                    threadDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case "safari":
                    if (headless) throw new IllegalArgumentException("Safari does not support headless mode.");
                    threadDriver.set(new SafariDriver());
                    break;
                default:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless=new");
                    threadDriver.set(new EdgeDriver(edgeOptions));
            }
            threadDriver.get().manage().window().setSize(new org.openqa.selenium.Dimension(1440, 1000));
            threadDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return threadDriver.get();
    }

    public static void quitDriver() {
        try {
            if (threadDriver.get() != null) {
                threadDriver.get().quit();
            }
        } finally {
            threadDriver.remove();
            threadBrowserName.remove();
        }
    }

    public static boolean hasDriver() {
        return threadDriver.get() != null;
    }

    public static String getThreadBrowserName() {
        return threadBrowserName.get();
    }

    public static void setThreadBrowserName(String browser) {
        String normalized = browser.trim().toLowerCase(Locale.ROOT);
        if (!java.util.Set.of("edge", "chrome", "firefox", "safari").contains(normalized)) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        threadBrowserName.set(normalized);
    }
}
