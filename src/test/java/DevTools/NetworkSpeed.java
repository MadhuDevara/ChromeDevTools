package DevTools;

import org.apache.commons.compress.utils.TimeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;
import org.openqa.selenium.devtools.v102.network.model.ConnectionType;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class NetworkSpeed {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();  // Or devTools.createSessionIfThereIsNotOne();

        // Enable network conditions
        devTools.send(Network.enable(Optional.empty(),
                Optional.empty(), Optional.empty()));

        // Simulate network conditions (offline, latency, download, upload, connection type)
        devTools.send(Network.emulateNetworkConditions(false, 3000,
                20000, 100000, Optional.of(ConnectionType.ETHERNET)));

        // Listen for network loading failures
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println(loadingFailed.getErrorText());
            System.out.println(loadingFailed.getTimestamp());
        });

        long startTime = System.currentTimeMillis();

        // Navigate to the target page
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        // Click the button to navigate within the app
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        long endTime = System.currentTimeMillis();

        // Output the time taken for the operation
        System.out.println(endTime - startTime);

        //Thread.sleep(3000);
        // Close the driver

        driver.close();
    }
}
