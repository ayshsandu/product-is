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

public class RecoveryFailurePage {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "back-to-signin")
    @CacheLookup
    private WebElement backToSignIn;

    @FindBy(id = "code")
    @CacheLookup
    private WebElement code;

    @FindBy(css = "a[href='http://wso2.com/']")
    @CacheLookup
    private WebElement inc;

    @FindBy(id = "message")
    @CacheLookup
    private WebElement message;

    private final String pageLoadedText = "Identity Server User Portal";

    private final String pageUrlTemplate = "/user-portal/recovery/failure?%s";

    public RecoveryFailurePage() {
    }

    public RecoveryFailurePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public RecoveryFailurePage(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public RecoveryFailurePage(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on Back To Sign In Link.
     *
     * @return the RecoveryFailurePage class instance.
     */
    public RecoveryFailurePage clickBackToSignInLink() {
        backToSignIn.click();
        return this;
    }

    /**
     * Click on Inc Link.
     *
     * @return the RecoveryFailurePage class instance.
     */
    public RecoveryFailurePage clickIncLink() {
        inc.click();
        return this;
    }

    /**
     * @return the WebElement
     */
    public WebElement code() {
        return code;
    }

    /**
     * @return the WebElement message
     */
    public WebElement message() {
        return message;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the RecoveryFailurePage class instance.
     */
    public RecoveryFailurePage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the RecoveryFailurePage class instance.
     */
    public RecoveryFailurePage verifyPageUrl(String query) {
        String url = String.format(pageUrlTemplate, query);
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(url);
            }
        });
        return this;
    }

    /**
     * Verify that current message
     *
     * @return true/false.
     */
    public boolean verifyMessage(String messageContent) {
        return message.getText().equals(messageContent);
    }

    /**
     * Verify that current code
     *
     * @return true/false.
     */
    public boolean verifyCode(String codeValue) {
        return code.getText().equals("Code : " + codeValue);
    }
}
