package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenChromeIncognito {
    public static void main(String[] args) {
        // Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Add the incognito mode argument
        options.addArguments("--incognito");

        // Initialize ChromeDriver with options
        ChromeDriver driver = new ChromeDriver(options);

        // Open a website in incognito mode
        driver.get("https://www.example.com");

        // Add any other actions here, like interacting with the webpage

        // Close the browser
        driver.quit();
    }
}
