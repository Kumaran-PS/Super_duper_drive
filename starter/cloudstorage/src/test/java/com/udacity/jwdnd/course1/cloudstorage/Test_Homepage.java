package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Test_Homepage {
    
    @FindBy(id = "logout") private WebElement logoutButtonCheck;
    @FindBy(id = "note-save") private WebElement noteSaveButtonCheck;
    @FindBy(id = "noteEdit") private WebElement noteEdit;
    @FindBy(id = "addNote") private WebElement addNoteCheck;
    @FindBy(id = "credentialsUrlPassword") private WebElement textCredentialsPasswordCheck;
    @FindBy(id = "noteDescriptionText") private WebElement textNoteDescriptionCheck;
    @FindBy(id = "credential-url") private WebElement credentialsUrlCheck;
    @FindBy(id = "note-title") private WebElement noteTitleCheck;
    @FindBy(id = "nav-notes-tab") private WebElement notesTabCheck;
    @FindBy(id = "note-description") private WebElement noteDescriptionCheck;
    @FindBy(id = "noteDelete") private WebElement noteDeleteCheck;
    @FindBy(id = "nav-credentials-tab") private WebElement credentialsTabCheck;
    @FindBy(id = "credential-username") private WebElement credentialsUsernameCheck;
    @FindBy(id = "addCredential") private WebElement addCredentialCheck;
    @FindBy(id = "credentialEdit") private WebElement editCredentialCheck;
    @FindBy(id = "noteTitleText") private WebElement textNoteTitleCheck;
    @FindBy(id = "credentialDelete") private WebElement deleteCredentialCheck;
    @FindBy(id = "credential-save") private WebElement saveCredentialCheck;
    @FindBy(id = "credentialsUrlUsername") private WebElement TextCredentialsUsernameCheck;
    @FindBy(id = "credentialsUrlText") private WebElement textCredentialsUrlCheck;
    @FindBy(id = "credential-password") private WebElement passwordCredentialsCheck;


    public void AddNewNote (){
        try{
            addNoteCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }

    }
    public void userlogout() {
        try{
            logoutButtonCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    public void getNotesTab() {notesTabCheck.click();}

    public Test_Homepage(WebDriver driver) {
        PageFactory.initElements(driver, this);}

    public void getCredentialsTab() {
        try{
            credentialsTabCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    public String getNoteTitleText() {return textNoteTitleCheck.getAttribute("innerHTML");}
    public void EditNote(){
        noteEdit.click();
    }
    public String getCredentialUrlText() {return textCredentialsUrlCheck.getAttribute("innerHTML");}
    public void clickAddaNewCredential(){
        try{
            addCredentialCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    public void EditCredential() {
        try{
            editCredentialCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    public void DeleteCredential(){
        try{
            deleteCredentialCheck.click();
        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    public void DeleteNote(){
        noteDeleteCheck.click();
    }
    public String getNoteDescriptionText() {return textNoteDescriptionCheck.getAttribute("innerHTML");}
    public String getCredentialUsernameText() {return TextCredentialsUsernameCheck.getAttribute("innerHTML");}
    public String getCredentialPasswordText() {return textCredentialsPasswordCheck.getAttribute("innerHTML");}
    public void addCredentialDetails(String url, String userName, String password) {
        credentialsUrlCheck.clear();
        credentialsUsernameCheck.clear();
        passwordCredentialsCheck.clear();
        credentialsUrlCheck.sendKeys(url);
        credentialsUsernameCheck.sendKeys(userName);
        passwordCredentialsCheck.sendKeys(password);
        saveCredentialCheck.click();
        
    }
    public void addNoteDetails(String title, String description) {
        noteTitleCheck.clear();
        noteDescriptionCheck.clear();
        noteTitleCheck.sendKeys(title);
        noteDescriptionCheck.sendKeys(description);
        noteSaveButtonCheck.click();
    }

    

}
