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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class PasswordRecoveryOptionsPage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "back-to-signin-1")
    @CacheLookup
    private WebElement backToSignIn1;

    @FindBy(id = "back-to-signin-2")
    @CacheLookup
    private WebElement backToSignIn2;

    @FindBy(id = "back-to-signin-5")
    @CacheLookup
    private WebElement backToSignIn3;

    @FindBy(id = "back-to-signin-6")
    @CacheLookup
    private WebElement backToSignIn4;

//    @FindBy(css = "a[href='http://wso2.com/']")
//    @CacheLookup
//    private WebElement inc;

    private final String pageLoadedText = "Please select a method to recover your password";
    private final String noQuestionsAnsweredTextContent = "You have not answered any security questions. Please " +
            "contact system administrator to recover your password.";
    private final String recoveryMessageText = "Recover Password for ";

    private final String pageUrlTemplate = "/user-portal/recovery/password-options?username=%s&domain=%s&userId=%s";

    @FindBy(id = "btn-pwd-recover-email")
    @CacheLookup
    private WebElement recover;

    @FindBy(id = "txt-heading")
    @CacheLookup
    private WebElement recoverPasswordForUser;

    @FindBy(id = "email-recovery-container")
    @CacheLookup
    private WebElement emailRecoveryContainer;

    @FindBy(id = "question-based-recovery-container")
    @CacheLookup
    private WebElement questionBasedRecoveryContainer;

    @FindBy(id = "no-questions-answered")
    @CacheLookup
    private WebElement noQuestionsAnsweredTxt;

    @FindBy(name = "recover-option")
    @CacheLookup
    private List<WebElement> recoverWithEmail;

    @FindBy(name = "recover-option")
    @CacheLookup
    private List<WebElement> recoverWithSecurityQuestion;

    private final String recoverWithSecurityQuestionValue = "security-question-recovery";

    private final String recoverWithEmailValue = "email-recovery";

    public PasswordRecoveryOptionsPage() {
    }

    public PasswordRecoveryOptionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public PasswordRecoveryOptionsPage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public PasswordRecoveryOptionsPage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage clickBackToSignIn1Link() {
        backToSignIn1.click();
        return this;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage clickBackToSignIn2Link() {
        backToSignIn2.click();
        return this;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage clickBackToSignIn3Link() {
        backToSignIn3.click();
        return this;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage clickBackToSignIn4Link() {
        backToSignIn4.click();
        return this;
    }

//    /**
//     * Click on Inc Link.
//     *
//     * @return the PasswordRecoveryOptionsPage class instance.
//     */
//    public PasswordRecoveryOptionsPage clickIncLink() {
//        inc.click();
//        return this;
//    }

    /**
     * Click on Recover Button.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage clickRecoverButton() {
        recover.click();
        return this;
    }

//    /**
//     * Fill every fields in the page.
//     *
//     * @return the PasswordRecoveryOptionsPage class instance.
//     */
//    public PasswordRecoveryOptionsPage fill() {
//        setRecoverWithEmailRadioButtonField();
//        setRecoverWithSecurityQuestionRadioButtonField();
//        return this;
//    }

//    /**
//     * Fill every fields in the page and submit it to target page.
//     *
//     * @return the PasswordRecoveryOptionsPage class instance.
//     */
//    public PasswordRecoveryOptionsPage fillAndSubmit() {
//        fill();
//        return submit();
//    }

    /**
     * Recover Password For Admin .
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage recoverPasswordForUser() {
        return this;
    }

    /**
     * Set default value to Recover With Security Question Radio Button field.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage setRecoverWithEmailRadioButtonField() {
        for (WebElement el : recoverWithEmail) {
            if (el.getAttribute("value").equals(recoverWithEmailValue)) {
                if (!el.isSelected()) {
                    el.click();
                }
                break;
            }
        }
        return this;
    }

    /**
     * Set default value to Recover With Security Question Radio Button field.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage setRecoverWithSecurityQuestionRadioButtonField() {
        for (WebElement el : recoverWithSecurityQuestion) {
            if (el.getAttribute("value").equals(recoverWithSecurityQuestionValue)) {
                if (!el.isSelected()) {
                    el.click();
                }
                break;
            }
        }
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage submit() {
        clickRecoverButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that the page shows correct heading message according to username
     *
     * @return the true/false.
     */
    public boolean verifyRecoveryMessage(String username) {
        return recoverPasswordForUser.getText().equals(recoveryMessageText + username);
    }

    public WebElement getEmailRecoveryContainer() {
        return emailRecoveryContainer;
    }

    public WebElement  getQuestionBasedRecoveryContainer() {
        return questionBasedRecoveryContainer;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the PasswordRecoveryOptionsPage class instance.
     */
    public PasswordRecoveryOptionsPage verifyPageUrl(String username, String domain, String id) {
        String message = String.format(pageUrlTemplate, username, domain, id);
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(message);
            }
        });
        return this;
    }

    public WebElement getNoQuestionsAnsweredTxt() {
        return noQuestionsAnsweredTxt;
    }

    public boolean verifyNoQuestionsAnsweredText() {
        return noQuestionsAnsweredTxt.getText().equals(noQuestionsAnsweredTextContent);
    }

    public WebElement getTxtQuestionElement(String questionSetId) {
        return driver.findElement(By.id("question-" + questionSetId));
    }

    public WebElement getInputQuestionAnswerElement(String questionSetId) {
        return driver.findElement(By.id(questionSetId));
    }

}

