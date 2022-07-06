package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test_Result {
    @FindBy(xpath = "//*[contains(@href,'home')]")
    private WebElement homeLinkUrl;

    public Test_Result(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        homeLinkUrl.click();
    }
}

