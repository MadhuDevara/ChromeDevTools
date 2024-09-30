
//
package DevTools;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.fetch.Fetch;
import org.openqa.selenium.devtools.v102.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v102.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkFailedRequest {
    public static void main(String[] args){

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> patterns = Optional.of(
                Arrays.asList(new RequestPattern(Optional.of("*GetBook"), Optional.empty(), Optional.empty()))
        );

        devTools.send(Fetch.enable(patterns, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request -> {
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        // Close the browser
        driver.quit();
    }
}

    /*
    The purpose of the NetworkFailedRequest code is to simulate and test how a website behaves when network requests fail.
    Specifically, it uses Chrome DevTools Protocol (CDP) to intercept and intentionally fail certain network requests made
    by the browser. Here's a breakdown of the key objectives:

    Network Request Interception:
    The code intercepts network requests sent from the browser to the server. It filters for requests that match a specific
    pattern (*GetBook in this case), which could be an API or resource call related to the website.

    Simulating Failed Network Requests:
    By using Fetch.failRequest(), it deliberately causes the matched network requests to fail with a specific error
    (ErrorReason.FAILED). This mimics scenarios where a server or network issue occurs, which can be used to test the
    website’s resilience to network failures.

    Testing Application Behavior:
    The goal is to observe how the website responds when crucial network requests fail. For instance, does the website
    display error messages, handle the failure gracefully, or break the functionality? This kind of testing is important
    for ensuring the site can handle real-world issues, such as connectivity problems or server outages.
    In summary, this code is useful for testing the error handling and resilience of a web application when specific
    network requests fail.
    */
