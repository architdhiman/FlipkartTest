package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Browser {
	public Properties properties = new Properties();

	public WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (properties.getProperty("browser.name").toString()) {
		case "chrome":

			if (properties.getProperty("headless").toString().equals("True")) {
				System.out.println("headless");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");

				// Initialize ChromeDriver with headless option
				driver = new ChromeDriver(options);
				driver.get("https://google.com");
			} else {
				driver = new ChromeDriver();
				driver.get("https://google.com");
			}

			break;
		case "FF":

			FirefoxOptions ffOptions = new FirefoxOptions();

			if (properties.getProperty("headless").toString().equals("True")) {
				System.out.println("headless");
				ffOptions.addArguments("--headless");
			}

			driver = new FirefoxDriver(ffOptions);
			driver.get("https://google.com");
			break;
		case "IE":

			InternetExplorerOptions ieOptions = new InternetExplorerOptions();

			driver = new InternetExplorerDriver(ieOptions);
			driver.get("https://google.com");
			break;
		}

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("global.wait.time"))));
		driver.get(properties.getProperty("applicationURL").toString());

	}

	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}
}
