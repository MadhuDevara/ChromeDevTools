package DevTools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.UsernameAndPassword;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthentication {

	public static void main(String[] args) {

		// Set up WebDriver with WebDriverManager
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Get DevTools instance and create a session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();  // Start DevTools session

		// Predicate to match the URI for authentication
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

		// Register the username and password for basic authentication
		((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));

		// Open the URL that requires basic authentication
		driver.get("http://httpbin.org/basic-auth/foo/bar");

		// Optionally, validate the page content or authentication status here

		// Close the browser
		driver.quit();
	}
}

	/*

	The purpose of the provided code is to automate a browser interaction using Selenium and handle basic authentication via Chrome DevTools.
	Here’s a breakdown of its main functionality:

	WebDriver Setup:
	The code uses WebDriverManager to automatically set up the ChromeDriver for controlling the Chrome browser.

	DevTools Integration:
	A DevTools session is created, allowing deeper interaction with the browser, including network-related features like handling authentication.

	Basic Authentication Handling:
	The code sets up a basic authentication process, which is a type of HTTP authentication where the server requests a username and password.
	A Predicate<URI> is used to specify the condition for which authentication is required (in this case, it targets the host httpbin.org).
	The credentials (foo as the username and bar as the password) are registered using the HasAuthentication interface provided by Selenium.

	Navigating to the URL:
	The driver navigates to a URL (http://httpbin.org/basic-auth/foo/bar) that requires basic authentication, and the credentials are automatically supplied.

	Closing the Browser:
	After interacting with the page, the browser is closed using driver.quit().
	This setup allows automated handling of websites that use basic HTTP authentication without needing to manually enter credentials in a pop-up or login form.
	*/
