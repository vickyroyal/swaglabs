import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwagLabsTest {

    public static void main(String[] args) throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";

        String wrongUsername = "Mohan_user";
        String wrongPassword = "123_sauce";
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        //testing wrong credientials
        login(driver, wrongUsername, wrongPassword);
        Thread.sleep(2000);
        driver.get("https://www.saucedemo.com/");
        // Login to Swag Labs
        login(driver, username, password);
        Thread.sleep(2000);

        // Select a product and add it to the cart
        selectProduct(driver, "Sauce Labs Backpack");
        Thread.sleep(2000);

        addToCart(driver);
        Thread.sleep(2000);


        // Remove the product from the cart
        removeFromCart(driver);
        Thread.sleep(2000);


        // Checkout
        checkout(driver);
        Thread.sleep(2000);


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

    public static void addToCart(WebDriver driver) {
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
    }

    public static void removeFromCart(WebDriver driver) {
        WebElement removeButton = driver.findElement(By.name("remove-sauce-labs-backpack"));
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

