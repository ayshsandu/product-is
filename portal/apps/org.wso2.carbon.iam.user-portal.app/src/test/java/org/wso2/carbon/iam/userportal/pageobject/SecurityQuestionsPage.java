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

public class SecurityQuestionsPage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "back-to-signin-5")
    @CacheLookup
    private WebElement backToSignIn1;

    @FindBy(id = "back-to-signin-6")
    @CacheLookup
    private WebElement backToSignIn2;

    @FindBy(id = "no-questions-answered")
    @CacheLookup
    private WebElement noQuestionsAnsweredTxt;

    @FindBy(css = "a[href='http://wso2.com/']")
    @CacheLookup
    private WebElement inc;

    @FindBy(id = "txt-heading")
    @CacheLookup
    private WebElement recoverPasswordForUser;


    private final String noQuestionsAnsweredTextContent = "You have not answered any security questions. Please " +
            "contact system administrator to recover  your password";

    private String pageUrlTemplate = "/user-portal/recovery/security-questions?username=%s&domain=%s&userId=%s";

    private final String recoveryMessageText = "Recover Password for ";

    public SecurityQuestionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SecurityQuestionsPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public SecurityQuestionsPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the SecurityQuestionsPage class instance.
     */
    public SecurityQuestionsPage clickBackToSignIn1Link() {
        backToSignIn1.click();
        return this;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the SecurityQuestionsPage class instance.
     */
    public SecurityQuestionsPage clickBackToSignIn2Link() {
        backToSignIn2.click();
        return this;
    }

    /**
     * Click on Inc Link.
     *
     * @return the SecurityQuestionsPage class instance.
     */
    public SecurityQuestionsPage clickIncLink() {
        inc.click();
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the SecurityQuestionsPage class instance.
     */
    public SecurityQuestionsPage verifyPageUrl(String username, String domain, String id) {
        String message = String.format(pageUrlTemplate, username, domain, id);
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(message);
            }
        });
        return this;
    }

    public WebElement getNoQuestionsansweredTxt() {
        return noQuestionsAnsweredTxt;
    }

    public boolean verifyNoQuestionsAnsweredText() {
        return noQuestionsAnsweredTxt.getText().equals(noQuestionsAnsweredTextContent);
    }

    /**
     * Verify that the page shows correct heading message according to username
     *
     * @return the true/false.
     */
    public boolean verifyRecoveryMessage(String username) {
        return recoverPasswordForUser.getText().equals(recoveryMessageText + username);
    }
}
