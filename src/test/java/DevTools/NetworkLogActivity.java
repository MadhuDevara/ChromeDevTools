 //
package DevTools;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;
import org.openqa.selenium.devtools.v102.network.model.Request;
import org.openqa.selenium.devtools.v102.network.model.Response;
import java.util.Optional;

public class NetworkLogActivity {
    public static void main(String[] args) {

        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty()
        , Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request ->{

            Request req = request.getRequest();
            System.out.println(req.getUrl());

        });

        devTools.addListener(Network.responseReceived(), reponse ->{

            Response res = reponse.getResponse();
            System.out.println(res.getUrl());
            System.out.println(res.getStatus());

        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']"));

    }
}

      /*
        The purpose of the **NetworkLogActivity** code is to capture and log network activity, specifically the requests and responses that are exchanged between the browser and the web server, during the loading of a webpage. Here's a breakdown of its key objectives:

        1. **Network Monitoring**:
        - The code leverages Chrome DevTools Protocol (CDP) to monitor network activity in the browser. By enabling the network domain with `Network.enable()`, it starts capturing network events such as requests being sent and responses being received.

        2. **Logging Requests**:
        - It listens for network requests being made by the browser using the `Network.requestWillBeSent()` event. For each request, the URL of the resource being requested is logged. This allows you to track which resources (like scripts, images, or API calls) are being requested by the browser.

        3. **Logging Responses**:
        - Similarly, it listens for responses with `Network.responseReceived()`. It logs both the URL of the response and its HTTP status code (e.g., 200 for success, 404 for not found). This is useful for verifying that the server returns the expected responses, and for identifying failed or slow requests.

        4. **Testing and Debugging**:
        - This kind of logging is useful in debugging network issues, understanding resource loading patterns, and verifying the flow of network communication during testing. For instance, you can check if all resources are loaded properly and if the server responses are as expected.

        ### Summary:
        This code is designed to **track and log network requests and responses** made by a web application during page interaction, which is helpful for **network performance analysis**, **debugging issues**, and **verifying API communication**.
      */


