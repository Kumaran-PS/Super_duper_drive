package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test_Signup {

    @FindBy(id = "inputFirstName") private WebElement firstNameInputCheck;

    @FindBy(tagName = "button") private WebElement signupButtonCheck;

    @FindBy(id = "inputLastName") private WebElement lastNameInputCheck;

    @FindBy(id = "inputPassword") private WebElement passwordInputCheck;

    @FindBy(id = "inputUserName") private WebElement userNameInputCheck;

    public Test_Signup(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signupUser(String fname, String lname, String user, String pwd) {
        passwordInputCheck.sendKeys(String.valueOf(pwd));
        firstNameInputCheck.sendKeys(String.valueOf(fname));
        userNameInputCheck.sendKeys(String.valueOf(user));
        lastNameInputCheck.sendKeys(String.valueOf(lname));
        signupButtonCheck.click();
    }
}
