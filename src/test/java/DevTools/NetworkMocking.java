package DevTools;

import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.fetch.Fetch;


public class NetworkMocking {

    public static void main(String[] args) {

        // Initialize ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools and create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network mocking using Fetch
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        // Add a listener to intercept network requests
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            // Get the URL from the requestPaused parameter
            String url = requestPaused.getRequest().getUrl();

            // If the URL contains "shetty", mock the URL by replacing part of it
            if (url.contains("shetty")) {
                String mockedUrl = url.replace("shetty", "BadGuy");
                System.out.println("Mocked URL: " + mockedUrl);

                // Continue the request with the mocked URL
                devTools.send(Fetch.continueRequest(
                        requestPaused.getRequestId(),
                        Optional.of(mockedUrl),  // Mocked URL
                        Optional.empty(),         // Optional method
                        Optional.empty(),         // Optional post data
                        Optional.empty(),         // Optional headers
                        Optional.empty()          // Optional should intercept
                ));
            } else {
                // Continue with the original request if no mock is needed
                devTools.send(Fetch.continueRequest(
                        requestPaused.getRequestId(),
                        Optional.empty(),         // Original URL
                        Optional.empty(),         // Optional method
                        Optional.empty(),         // Optional post data
                        Optional.empty(),         // Optional headers
                        Optional.empty()          // Optional should intercept
                ));
            }
        });

        // Navigate to the test site
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        // Click on a button that navigates to the library section
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        // Pause for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the text from a paragraph element
        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        // Close the browser
        driver.quit();
    }
}
