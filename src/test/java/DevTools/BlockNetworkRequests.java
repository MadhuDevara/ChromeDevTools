
//
package DevTools;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;

import java.util.Optional;

public class BlockNetworkRequests {

    public static void main(String[] args){

        ChromeDriver driver = new ChromeDriver(); // Create a chrome driver instance

        DevTools devTools = driver.getDevTools(); // Get DevTools instance
        devTools.createSession(); // Create a new Devtools session

        // enable the network domain interact with network traffic
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //Block URLs matching jpg and css
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg" ,"*.css")));

        long startTime = System.currentTimeMillis(); // Record the start time

        // Perform browser actions
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();

        // Print out  the text found in a paragraph element
        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        long endTime = System.currentTimeMillis(); // Record the end time
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        //driver.quit();

    }

}


/*
    Purpose of BlockNetworkRequests Code
    This Java code is designed to interact with Chrome's DevTools to block certain types of network requests (like images and CSS files) while performing browser automation tasks using Selenium. The following is a breakdown of the code and its purpose:
    Purpose:
    The code uses Selenium's DevTools Protocol to manipulate browser network behavior, specifically blocking requests for .jpg and .css files. This can be useful in testing scenarios where you want to simulate network conditions, speed up load times, or test how a web page behaves without loading certain resources like images or stylesheets.
    Detailed Breakdown:
    1. ChromeDriver Initialization:
    Creates a new instance of ChromeDriver to control the Chrome browser.
    2. DevTools Session:
    This initiates a connection to Chrome's DevTools. DevTools provide more advanced browser control, such as blocking resources, simulating network conditions, etc.
    3. Enable Network Domain:
    Enables the browser's network domain, allowing the script to interact with network traffic and manipulate it (e.g., blocking resources).
    4. Blocking Network Requests:
    Blocks all URLs that match the specified patterns, which in this case are any requests for .jpg (images) and .css (stylesheets) files. This is helpful to prevent the browser from downloading images and CSS files, improving performance in certain tests.
    5. Perform Actions on a Web Page:
    Navigates to a web page (rahulshettyacademy.com), clicks on "Browse Products", then "Selenium", and finally clicks the "Add to Cart" button.
    6. Extract and Print Text:
    After performing the browser actions, it retrieves and prints the text inside a paragraph element (<p>).
    7. Measure Time Taken:
    The script records and prints the time taken to perform these browser operations, helping to assess performance improvements by blocking certain resources.
    Use Case:
            - Network Manipulation for Testing: By blocking images and CSS files, you can test how your application behaves with missing resources.
    - Performance Optimization: This can simulate performance testing scenarios where certain resources are delayed or missing.
    - Headless Testing: You might want to block unnecessary assets like images or CSS in automation tasks where visual rendering is not needed.
*/
