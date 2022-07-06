package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test_Login {

    @FindBy(tagName = "button") private WebElement loginButtonCheck;

    @FindBy(id = "inputUsername") private WebElement userNameInputCheck;

    @FindBy(id = "inputPassword") private WebElement passwordInputCheck;

    public Test_Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void loginUser(String username, String pwd) {
        passwordInputCheck.sendKeys(String.valueOf(pwd));
        userNameInputCheck.sendKeys(String.valueOf(username));
        loginButtonCheck.click();
    }
}
