import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SwagLabsTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.className("login_credentials"));
        WebElement password = driver.findElement(By.className("login_password"));
        String usernameList[] = username.getText().split("\n");
        String passwordList[] = password.getText().split("\n");
        for (int i = 1; i < usernameList.length; i++) {
            driver.get("https://www.saucedemo.com/");
            for (int j = 1; j < passwordList.length; j++) {
//                login(driver, usernameList[i], passwordList[j]);
                System.out.println("Elements are: " + usernameList[i]);
                System.out.println("Elements are: " + passwordList[j]);
                login(driver, usernameList[i], passwordList[j]);
                Thread.sleep(2000);
                // Select a product and add it to the cart
                try {
                    WebElement element = driver.findElement(By.className("inventory_item_name"));
                    String productName = element.getText();
                    System.out.println("productName == " + productName);
                    selectProduct(driver, productName);
                    Thread.sleep(2000);
                    addToCart(driver, productName);
                    Thread.sleep(2000);
                    // Remove the product from the cart
                    removeFromCart(driver, productName);
                    Thread.sleep(2000);
                    // Checkout
                    checkout(driver);
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
            }
        }

        driver.quit();

    }


    public static void login(WebDriver driver, String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public static void selectProduct(WebDriver driver, String productName) {
        WebElement productLink = driver.findElement(By.linkText(productName));
        productLink.click();
    }

    public static void addToCart(WebDriver driver, String name) {
        String id = name.replaceAll(" ", "-");
        String newName = "add-to-cart-" + id;
        WebElement addToCartButton = driver.findElement(By.name(newName.toLowerCase()));
        addToCartButton.click();
    }

    public static void removeFromCart(WebDriver driver, String name) {
        String id = name.replaceAll(" ", "-");
        String newName = "remove-" + id;
        WebElement removeButton = driver.findElement(By.name(newName.toLowerCase()));
        removeButton.click();
    }

    public static void checkout(WebDriver driver) {
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement zipField = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.id("continue"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");
        zipField.sendKeys("12345");
        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Check if the order was successful
        WebElement confirmationText = driver.findElement(By.className("complete-header"));
        System.out.println("Order confirmation: " + confirmationText.getText());
    }
}

