package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;

import java.util.Optional;

public class ChromeDevToolsExample {
    public static void main(String[] args) {

        // Initialize WebDriver
        ChromeDriver driver = new ChromeDriver();

        // Create DevTools instance and open session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable Network events
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Capture and log network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            System.out.println("Request URL: " + request.getRequest().getUrl());
        });

        // Open a webpage
        driver.get("https://www.google.com");

        // Close the browser
        driver.quit();
    }
}
