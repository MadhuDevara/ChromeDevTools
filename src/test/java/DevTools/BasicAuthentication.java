package ChromeDevTools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthentication {

	public static void main(String[] args) {


		
	WebDriverManager.chromedriver().setup();	
	WebDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		
		//Predicate, consumer
		
		Predicate<URI> uriPridicate = uri -> uri.getHost().contains("httpbin.org");
		
		((HasAuthentication)driver).register(uriPredicate,UsernameandPassword.of("foo","bar"))
		
		driver.get("http://httpbin.org/basic-auth/foo/bar");
		
		
		
		

	}

}
