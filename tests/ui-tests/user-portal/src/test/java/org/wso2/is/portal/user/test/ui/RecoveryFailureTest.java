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
import org.wso2.carbon.iam.userportal.pageobject.RecoveryFailurePage;
import org.wso2.is.user.portal.test.driver.SelectWebDriver;

public class RecoveryFailureTest extends SelectWebDriver {

    private RecoveryFailurePage recoveryFailurePage;
    private static String driverType = System.getProperty("driver");
    private static WebDriver driver = selectDriver(driverType);
    private static String recoveryCompletePageURI = "https://" + System.getProperty("home") + ":" +
            System.getProperty("port") + "/user-portal/recovery/failure?";

    @Test(groups = "recoveryFailurePageTest")
    public void testRecoveryFailurePageLoad() throws Exception {
        driver.get(recoveryCompletePageURI + "code=356156&status=18001");
        recoveryFailurePage = new RecoveryFailurePage(driver);
        Assert.assertNotNull(recoveryFailurePage.verifyPageLoaded(), "Page is not loaded successfully");
        Assert.assertNotNull(recoveryFailurePage.message(), "Error message is not visible");
        Assert.assertNotNull(recoveryFailurePage.code(), "Code is not visible");
        recoveryFailurePage.clickBackToSignInLink();
        WebElement forgotPasswordLnk = LoginPage.lnkForgotPassword(driver);
        Assert.assertNotNull(forgotPasswordLnk, "Back to Sign in link is not working");
    }

    @Test(groups = "recoveryFailurePageTest")
    public void testRecoveryFailureMessageWithStatus() throws Exception {
        String code = "45646468676873783894859";
        String status = "18001";
        String message = "Invalid code provided with the recovery request";
//        error.20008=Invalid Answer(s) Provided for the Security question(s)
//        error.18001=Invalid code provided with the recovery request
//        error.18002=Expired code provided with the recovery request
        driver.get(recoveryCompletePageURI + "code=" + code + "&status=" + status);
        recoveryFailurePage = new RecoveryFailurePage(driver);
        Assert.assertTrue(recoveryFailurePage.verifyCode(code), "Correct code is not visible");
        Assert.assertTrue(recoveryFailurePage.verifyMessage(message), "Correct message is not visible for status: " +
                status);
    }

    @Test(groups = "recoveryFailurePageTest")
    public void testRecoveryFailureMessageWithoutCode() throws Exception {
        String status = "18001";
        String message = "Invalid code provided with the recovery request";
        driver.get(recoveryCompletePageURI + "&status=" + status);
        recoveryFailurePage = new RecoveryFailurePage(driver);
        Assert.assertNotNull(recoveryFailurePage.verifyPageLoaded(), "Page is not loaded successfully");
        Assert.assertTrue(recoveryFailurePage.verifyMessage(message), "Correct message is not visible for status: " +
                status);
    }
}
