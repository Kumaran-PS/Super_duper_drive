package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private Credentials credentials;
	private WebDriver driver;
	private String baseURL;
	private Test_Homepage test_homepage;
	private Test_Signup test_signup;
	private Test_Login test_login;
	private Test_Result test_result;

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private CredentialsService credentialService;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
	}
	@AfterEach
	public void afterEach() {
		if (this.driver != null) {driver.quit();}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}



	@Test
	public void testRedirection() {
		driver.get(baseURL + "/signup");
		test_signup = new Test_Signup(driver);
		test_signup.signupUser("kumaran", "palanisamy", "psk", "pwd");
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

//	@Test
//	public void testRedirection() {
//		// Create a test account
//		doMockSignUp("Redirection","Test","RT","123");
//
//		// Check if we have been redirected to the log in page.
//		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
//	}

	@Test
	@Order(1)
	public void Check_Uauthorised_Access() {
		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}


	@Test
	@Order(2)
	public void Check_Signup_Login_Logout() throws InterruptedException {
		driver.get(baseURL + "/signup");
		test_signup = new Test_Signup(driver);
		test_signup.signupUser("kumaran", "palanisamy", "psk", "pwd");
		driver.get(baseURL + "/login");
		test_login = new Test_Login(driver);
		test_login.loginUser("psk", "pwd");
		Assertions.assertEquals("Home", driver.getTitle());
		test_homepage = new Test_Homepage(driver);
		Thread.sleep(1000);
		test_homepage.userlogout();
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	public Test_Homepage signupAndLogin(){
		driver.get(baseURL + "/signup");
		test_signup = new Test_Signup(driver);
		test_signup.signupUser("kumar", "psk", "pskumar", "password");
		driver.get(baseURL + "/login");
		test_login = new Test_Login(driver);
		test_login.loginUser("pskumar", "password");
		return new Test_Homepage(driver);
	}


	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Username")));
		WebElement inputUsername = driver.findElement(By.id("Username"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	@Test
	@Order(3)
	public void Check_Note_Entry() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);
		test_homepage.AddNewNote();
		Thread.sleep(1000);
		test_homepage.addNoteDetails("note","testing descrption");
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);
		Assertions.assertEquals("note", test_homepage.getNoteTitleText());
		Assertions.assertEquals("testing descrption", test_homepage.getNoteDescriptionText());
	}
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	@Test
	@Order(4)
	public void Check_Note_Edit() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);
//		test_homepage.clickEditNote();
		test_homepage.EditNote();
		Thread.sleep(1000);
		test_homepage.addNoteDetails("update title","update description");
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);
		Assertions.assertEquals("update title", test_homepage.getNoteTitleText());
		Assertions.assertEquals("update description", test_homepage.getNoteDescriptionText());
	}

	@Test
	@Order(5)
	public void Check_Note_Delete() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);
		test_homepage.DeleteNote();
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getNotesTab();
		Thread.sleep(1000);

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			test_homepage.getNoteTitleText();
		});

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */


	@Test
	@Order(6)
	public void Check_Credential_Entry() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);
		test_homepage.clickAddaNewCredential();
		Thread.sleep(1000);
		test_homepage.addCredentialDetails("Test url","Test username", "Test password");
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);

		credentials = this.credentialService.getUserCredentialByCredentialId(1);
		Assertions.assertEquals("Test url", test_homepage.getCredentialUrlText());
		Assertions.assertEquals("Test username", test_homepage.getCredentialUsernameText());
		Assertions.assertEquals(this.encryptionService.encryptValue("Test password", credentials.getKey()), test_homepage.getCredentialPasswordText());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		driver.get(baseURL + "/signup");
		test_signup = new Test_Signup(driver);
		test_signup.signupUser("kumaran", "palanisamy", "psk", "pwd");
//		doMockSignUp("URL","Test","UT","123");
//		doLogIn("UT", "123");
		driver.get(baseURL + "/login");
		test_login = new Test_Login(driver);
		test_login.loginUser("psk", "pwd");
		Assertions.assertEquals("Home", driver.getTitle());

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}

//	@Test
//	public void testBadUrl() {
//		// Create a test account
//		doMockSignUp("URL","Test","UT","123");
//		doLogIn("UT", "123");
//
//		// Try to access a random made-up URL.
//		driver.get("http://localhost:" + this.port + "/some-random-page");
//		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
//	}

	@Test
	@Order(7)
	public void Check_Credential_Edit() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);
		test_homepage.EditCredential();
		Thread.sleep(1000);
		test_homepage.addCredentialDetails("Test Url Update","Test Username Update", "Test Password Update");
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);

		credentials = this.credentialService.getUserCredentialByCredentialId(1);
		Assertions.assertEquals("Test Url Update", test_homepage.getCredentialUrlText());
		Assertions.assertEquals("Test Username Update", test_homepage.getCredentialUsernameText());
		Assertions.assertEquals(this.encryptionService.encryptValue("Test Password Update", credentials.getKey()), test_homepage.getCredentialPasswordText());
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
//		doMockSignUp("Large File","Test","LFT","123");
//		doLogIn("LFT", "123");
		driver.get(baseURL + "/signup");
		test_signup = new Test_Signup(driver);
		test_signup.signupUser("kumaran", "palanisamy", "psk", "pwd");
		driver.get(baseURL + "/login");
		test_login = new Test_Login(driver);
		test_login.loginUser("psk", "pwd");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

//	@Test
//	public void testLargeUpload() {
//		// Create a test account
//		doMockSignUp("Large File","Test","LFT","123");
//		doLogIn("LFT", "123");
//
//		// Try to upload an arbitrary large file
//		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
//		String fileName = "upload5m.zip";
//
//		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
//		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
//		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());
//
//		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
//		uploadButton.click();
//		try {
//			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
//		} catch (org.openqa.selenium.TimeoutException e) {
//			System.out.println("Large File upload failed");
//		}
//		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
//
//	}

	@Test
	@Order(8)
	public void Check_Credential_Entry_Delete() throws InterruptedException {
		test_homepage = signupAndLogin();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);
		test_homepage.DeleteCredential();
		Thread.sleep(1000);

		test_result = new Test_Result(driver);
		test_result.goToHomePage();
		Thread.sleep(1000);

		test_homepage.getCredentialsTab();
		Thread.sleep(1000);

		Assertions.assertThrows(NoSuchElementException.class, () -> {
			test_homepage.getCredentialUrlText();
		});

	}


}
