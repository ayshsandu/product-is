/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.iam.userportal.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class RecoveryCompletePage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "back-to-signin")
    @CacheLookup
    private WebElement backToSignIn;

    @FindBy(css = "a[href='http://wso2.com/']")
    @CacheLookup
    private WebElement inc;

    @FindBy(id = "password-recovery-email")
    @CacheLookup
    private WebElement passwordRecoveryEmail;

    @FindBy(id = "username-recovery")
    @CacheLookup
    private WebElement usernameRecovery;

    private final String emailSentText = "An Email has been sent to your email address";
    private final String passwordRecoverHeading = "Recover Password";
    private final String usernameRecoverHeading = "Recover Username";
    private final String usernameRecoveredText = "An Email has been sent to your email address with username";
    private String pageUrlTemplate = "/user-portal/recovery/complete?%s=true";

    public RecoveryCompletePage() {
    }

    public RecoveryCompletePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public RecoveryCompletePage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public RecoveryCompletePage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the RecoveryCompletePage class instance.
     */
    public RecoveryCompletePage clickBackToSignInLink() {
        backToSignIn.click();
        return this;
    }

    /**
     * Click on Inc Link.
     *
     * @return the RecoveryCompletePage class instance.
     */
    public RecoveryCompletePage clickIncLink() {
        inc.click();
        return this;
    }

    /**
     * Recover Password .
     *
     * @return the WebElement .
     */
    public WebElement passwordRecoveryEmail() {
        return passwordRecoveryEmail;
    }

    /**
     * Recover Password .
     *
     * @return the RecoveryCompletePage class instance.
     */
    public WebElement usernameRecovery() {
        return usernameRecovery;
    }

    /**
     * Verify that the username sent in email loaded completely.
     *
     * @return true/false.
     */
    public boolean verifyUsernameRecoveredMessage() {
        return driver.getPageSource().contains(usernameRecoveredText);
    }

    /**
     * Verify that the email sent message loaded completely.
     *
     * @return true/false.
     */
    public boolean verifyEmailSentForPasswordRecoveredMessage() {
        return driver.getPageSource().contains(emailSentText);
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the RecoveryCompletePage class instance.
     */
    public RecoveryCompletePage verifyPageUrl(String type) {
        String url = String.format(pageUrlTemplate, type);
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(url);
            }
        });
        return this;
    }
}
