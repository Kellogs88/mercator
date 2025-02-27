import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SauceDemoExercise {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium_Drivers\\ChromeDriver.exe");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        //Maximise the browser window
        driver.manage().window().maximize();

        try {
            // Step 1: Navigate to the URL
            driver.get("https://www.saucedemo.com");

            // Step 2: Login using the credentials
            WebElement username = driver.findElement(By.id("user-name"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            username.sendKeys("standard_user");
            password.sendKeys("secret_sauce");
            loginButton.click();

            // Step 3: Find and select the highest priced item
            List<WebElement> items = driver.findElements(By.className("inventory_item"));
            WebElement highestPriceItem = null;
            double highestPrice = 0;

            for (WebElement item : items) {
                WebElement priceElement = item.findElement(By.className("inventory_item_price"));
                double price = Double.parseDouble(priceElement.getText().replace("$", ""));
                if (price > highestPrice) {
                    highestPrice = price;
                    highestPriceItem = item;
                }
            }

            // Step 4: Add the highest price item to the cart
            WebElement addToCartButton = highestPriceItem.findElement(By.className("btn_inventory"));
            addToCartButton.click();

            // Additional Verification - check item is added to cart
            WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
            if (cartBadge.isDisplayed()) {
                System.out.println("Test Passed: Item added to cart.");
            } else {
                System.out.println("Test Failed: Item not added to cart.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
