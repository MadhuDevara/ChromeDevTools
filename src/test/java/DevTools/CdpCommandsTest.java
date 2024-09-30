
//

package DevTools;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import java.util.HashMap;
import java.util.Map;

public class CdpCommandsTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();  // Get DevTools instance
        devTools.createSession();   // Create a new session

        // Create a map for device metrics
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 600);
        deviceMetrics.put("height", 1000);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);

        // Use the CDP command to emulate device metrics
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics );

        // Perform browser actions
        driver.get("http://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector(".navbar-toggler")).click();

        Thread.sleep(3000);

        driver.findElement(By.linkText("Library")).click();
    }
}

    /*
    The purpose of the code is to demonstrate how to use Selenium's ChromeDriver along with the Chrome DevTools Protocol (CDP) to emulate specific device metrics for mobile testing.

    1. **Setup**: It initializes a ChromeDriver instance and creates a DevTools session to interact with the browser's capabilities.

    2. **Device Metrics**: A map is created to define the emulated device's characteristics, including width, height, device scale factor, and whether it's a mobile device.

    3. **Emulation Command**: It sends a command to the browser to apply these device metrics, effectively simulating a mobile device environment.

    4. **Navigation**: The code navigates to a specified URL, interacts with the webpage by clicking on elements, and waits for a few seconds before performing further actions.

    Overall, it allows testing how a web application behaves on a mobile device by modifying the browser's viewport settings without needing an actual mobile device.
    */
