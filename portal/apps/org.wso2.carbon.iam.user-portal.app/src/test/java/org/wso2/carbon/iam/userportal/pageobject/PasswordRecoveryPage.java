package org.wso2.carbon.iam.userportal.pageobject;

//import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class PasswordRecoveryPage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "back-to-signin-1")
    @CacheLookup
    private WebElement backToSignIn1;

    @FindBy(id = "back-to-signin-2")
    @CacheLookup
    private WebElement backToSignIn2;

    @FindBy(id = "input-recovery-btn")
    @CacheLookup
    private WebElement continueBtn;

    private final String pageLoadedText = "Please enter your username to continue";

    private final String pageUrl = "/user-portal/recovery/password";

    @FindBy(id = "username")
    @CacheLookup
    private WebElement username;

    public PasswordRecoveryPage() {
    }

    public PasswordRecoveryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PasswordRecoveryPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public PasswordRecoveryPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage clickBackToSignIn1Link() {
        backToSignIn1.click();
        return this;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage clickBackToSignIn2Link() {
        backToSignIn2.click();
        return this;
    }

    /**
     * Click on Continue Button.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage clickContinueButton() {
        continueBtn.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage fill() {
        setUsernameTextField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Username Text field.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage setUsernameTextField() {
        return setUsernameTextField(data.get("USERNAME"));
    }

    /**
     * Set value to Username Text field.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage setUsernameTextField(String usernameValue) {
        username.sendKeys(usernameValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage submit() {
        clickContinueButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until((WebDriver d) -> d.getPageSource().contains(pageLoadedText));
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the PasswordRecoveryPage class instance.
     */
    public PasswordRecoveryPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until((WebDriver d) -> d.getCurrentUrl().contains(pageUrl));
        return this;
    }
}
