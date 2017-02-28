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

package org.wso2.is.portal.user.test.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wso2.carbon.iam.userportal.pageobject.LoginPage;
import org.wso2.carbon.iam.userportal.pageobject.RecoveryCompletePage;
import org.wso2.is.user.portal.test.driver.SelectWebDriver;

public class RecoveryCompleteTest extends SelectWebDriver {

    private RecoveryCompletePage recoveryCompletePage;
    private static String driverType = System.getProperty("driver");
    private static WebDriver driver = selectDriver(driverType);
    private static String recoveryCompletePageURI = "https://" + System.getProperty("home") + ":" +
            System.getProperty("port") + "/user-portal/recovery/complete?";

    @Test(groups = "recoveryCompletePageTest")
    public void testPasswordRecoveryCompletePage() throws Exception {
        driver.get(recoveryCompletePageURI + "password=true");
        recoveryCompletePage = new RecoveryCompletePage(driver);
        Assert.assertNotNull(recoveryCompletePage.passwordRecoveryEmail(), "Password Recovery Heading is not visible");
        Assert.assertTrue(recoveryCompletePage.verifyEmailSentForPasswordRecoveredMessage(),
                "Email sent with password message is not visible");
        recoveryCompletePage.clickBackToSignInLink();
        WebElement forgotPasswordLnk = LoginPage.lnkForgotPassword(driver);
        Assert.assertNotNull(forgotPasswordLnk, "Back to Sign in link is not working");
    }

    @Test(groups = "recoveryCompletePageTest")
    public void testUsernameRecoveryCompletePage() throws Exception {
        driver.get(recoveryCompletePageURI + "username=true");
        recoveryCompletePage = new RecoveryCompletePage(driver);
        Assert.assertNotNull(recoveryCompletePage.usernameRecovery(), "username Recovery Heading is not visible");
        Assert.assertTrue(recoveryCompletePage.verifyUsernameRecoveredMessage(),
                "Email sent with username message is not visible");
        recoveryCompletePage.clickBackToSignInLink();
        WebElement forgotPasswordLnk = LoginPage.lnkForgotPassword(driver);
        Assert.assertNotNull(forgotPasswordLnk, "Back to Sign in link is not working");
    }

    @Test(groups = "recoveryCompletePageTest")
    public void testRedirectingToLoginPageWithInvalidRecoveryCompletePage() throws Exception {
        driver.get(recoveryCompletePageURI);
        WebElement forgotPasswordLnk = LoginPage.lnkForgotPassword(driver);
        Assert.assertNotNull(forgotPasswordLnk, "Redirect to Sign in link is not working");
    }
}
