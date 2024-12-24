package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationPage {

	WebDriver driver;
	WebDriverWait wait;

	public NotificationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

	public boolean verifyNotificationPage() {
		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Desktop Notifications')]")));
		return heading.isDisplayed();
	}

}
