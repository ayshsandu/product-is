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
import org.wso2.carbon.iam.userportal.actionobject.LoginPageAction;
import org.wso2.carbon.iam.userportal.pageobject.LoginPage;
import org.wso2.carbon.iam.userportal.pageobject.PasswordRecoveryOptionsPage;
import org.wso2.carbon.iam.userportal.pageobject.PasswordRecoveryPage;
import org.wso2.is.user.portal.test.driver.SelectWebDriver;

//import org.wso2.carbon.iam.userportal.pageobject.SignUpPage;

//import java.util.HashMap;
//import java.util.Map;

public class PasswordRecoveryTest extends SelectWebDriver {

    private static LoginPageAction loginPageAction = new LoginPageAction();
    private PasswordRecoveryPage passwordRecoveryPage;
    private PasswordRecoveryOptionsPage passwordRecoveryOptionsPage;
    private static WebDriver driver;
    private static String driverType = System.getProperty("driver");
    private static String loginPageUrl = "https://" + System.getProperty("home") + ":" +
            System.getProperty("port") + "/user-portal/login";

    @Test(groups = "initiatePasswordRecoveryTest")
    public void goToPasswordRecoveryPage() throws Exception {
        driver = selectDriver(driverType);
        driver.get(loginPageUrl);
        loginPageAction.clickForgetPassword(driver);
    }

    @Test(groups = "passwordRecoveryTest", dependsOnMethods = "goToPasswordRecoveryPage")
    public void loadPasswordRecoveryPage() throws Exception {
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.verifyPageLoaded();
        passwordRecoveryPage.verifyPageUrl();
    }

    @Test(groups = "passwordRecoveryTest", dependsOnMethods = "loadPasswordRecoveryPage")
    public void backToSignIN() throws Exception {
        passwordRecoveryPage.clickBackToSignIn1Link();
        WebElement forgotPasswordLnk = LoginPage.lnkForgotPassword(driver);
        Assert.assertNotNull(forgotPasswordLnk, "Back to Sign in link is not working");
        loginPageAction.clickForgetPassword(driver);
    }

    @Test(groups = "passwordRecoveryTest", dependsOnMethods = "backToSignIN")
    public void submitUsernameForRecovery() throws Exception {
        String username = System.getProperty("username");
        passwordRecoveryPage.setUsernameTextField(username);
        passwordRecoveryPage.submit();
        passwordRecoveryOptionsPage = new PasswordRecoveryOptionsPage(driver);
        passwordRecoveryOptionsPage.verifyPageLoaded();
        Assert.assertTrue(passwordRecoveryOptionsPage.verifyRecoveryMessage(username),
                "Correct Password Recovery Message is not appeared.");
    }

    @Test(groups = "passwordRecoveryTest", dependsOnMethods = "submitUsernameForRecovery")
    public void recoveryWithSecurityQuestionsOption() throws Exception {
        passwordRecoveryOptionsPage.setRecoverWithSecurityQuestionRadioButtonField();
        Assert.assertTrue(passwordRecoveryOptionsPage.getQuestionBasedRecoveryContainer().isDisplayed(),
                "QuestionBasedRecoveryContainer is not displayed");
        Assert.assertFalse(passwordRecoveryOptionsPage.verifyNoQuestionsAnsweredText(),
                "NoQuestionsAnsweredText is not displayed");
    }

//    @Test(groups = "passwordRecoveryTestWithQuestions", dependsOnMethods = "recoveryWithSecurityQuestionsOption")
//    public void recoveryWithSecurityQuestionsAdded() throws Exception {
//        passwordRecoveryOptionsPage.clickBackToSignIn1Link();
//        loginPageAction.signUp(driver);
//        Map<String, String> data = new HashMap<>();
//        data.put("USERNAME", "Ayesha");
//        data.put("PASSWORD", "Ayesha123!");
//        data.put("CONFIRM_PASSWORD", "Ayesha123!");
//        data.put("FIRST_NAME", "Ayesha");
//        data.put("LAST_NAME", "Dissanayaka");
//        data.put("EMAIL", "ayesha@wso2.com");
//
//        SignUpPage signUpPage = new SignUpPage(driver, data);
//        signUpPage.fillAndSubmit();
//    }

//    @Test(groups = "passwordRecoveryTestWithQuestions", dependsOnMethods = "recoveryWithSecurityQuestionsOption")
//    public void recoveryWithSecurityQuestionsAdded() throws Exception {
//        loadForgetPasswordRecoveryPage("Ayesha");
////        passwordRecoveryOptionsPage.verifyPageUrl("Ayesha", "PRIMARY", "");
//        passwordRecoveryOptionsPage.verifyPageLoaded();
//        String questionSetId = "http://wso2.org/claims/challengeQuestion1";
//        WebElement question = passwordRecoveryOptionsPage.getTxtQuestionElement(questionSetId);
//        Assert.assertNotNull(question, "Security Question is not loaded");
//        Assert.assertEquals(question.getText(), "City where you were born ?", "Correct question is not asked.");
//
//
//    }
//
//    private void loadForgetPasswordRecoveryPage(String username) {
//        driver.get(loginPageUrl);
//        loginPageAction.clickForgetPassword(driver);
//        passwordRecoveryPage = new PasswordRecoveryPage(driver);
//        passwordRecoveryPage.setUsernameTextField(username);
//        passwordRecoveryPage.submit();
//    }
}
