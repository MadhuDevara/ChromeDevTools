
// ChromeDevTools Mobile Emulator test

package DevTools;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.emulation.Emulation;
import org.openqa.selenium.devtools.v102.network.Network;
import java.util.Optional;

public class MobileEmulatorTest {
    public static void main(String[] args) throws InterruptedException {

        // Initialize ChromeDriver
        ChromeDriver driver = new ChromeDriver();

        // Get DevTools and create a session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Emulate mobile device
        devTools.send(Emulation.setDeviceMetricsOverride(
                375, // Width (common mobile width)
                812, // Height (common mobile height)
                100, // Device scale factor
                true, // Mobile view (true)
                Optional.empty(), // Screen width
                Optional.empty(),  // Screen height
                Optional.empty(),   // Screen x position
                Optional.empty(),   // Screen y position
                Optional.empty(),  // Overlap
                Optional.empty(), // View width
                Optional.empty(), // View height
                Optional.empty(), // Screen orientation
                Optional.empty()  // Viewport
        ));

        // Optionally enable Network domain if you want to capture network requests
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));

        // Listen for network requests if needed
        devTools.addListener(Network.requestWillBeSent(), request -> {
            System.out.println("Request URL: " + request.getRequest().getUrl());
        });

        // Load website
        driver.get("http://rahulshettyacademy.com/angularAppdemo/");

        // Simulate interaction with mobile interface (clicking the hamburger menu)
        driver.findElement(By.cssSelector(".navbar-toggler")).click();

        // Wait for the menu to open and navigate
        Thread.sleep(3000);

        // Click on the 'Library' link
        driver.findElement(By.linkText("Library")).click();

        // Close the browser
        driver.quit();
    }
}



        /*
        The purpose of the **Mobile Emulator Test**
        is to simulate how a website behaves on a mobile device using Selenium WebDriver and Chrome DevTools Protocol
        (CDP). Here's a breakdown of the main objectives:

        1. **Mobile Device Simulation**:
        - It emulates a mobile environment by setting device metrics like screen width, height, and device scale factor
         (resolution). This helps you observe how the website layout and functionality adapt to smaller screens, such
         as those of smartphones.

        2. **Responsive Design Testing**:
        - The code ensures that the website is mobile-responsive, meaning the layout and elements (like menus and
        buttons) adjust properly for mobile users. For instance, the code simulates interacting with a hamburger menu,
        which is a common design element on mobile sites.

        3. **Network Request Monitoring (Optional)**:
        - The code enables network tracking, which allows you to capture network requests sent by the website during
        interaction. This is useful for verifying that the website's resources (like APIs and images) are correctly
        loaded in the mobile view.

        4. **User Interaction Simulation**:
        - It mimics user interactions on a mobile device, such as clicking the navigation menu (hamburger icon)
        and selecting a link, to ensure these actions work seamlessly on mobile platforms.

        In essence, the goal is to test the website’s performance, layout, and functionality in a mobile environment
        without needing a physical mobile device.

        */
