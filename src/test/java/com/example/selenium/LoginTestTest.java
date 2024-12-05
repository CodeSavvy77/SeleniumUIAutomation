package com.example.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTestTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginTest loginTest = new LoginTest();
        loginTest.driver = this.driver; // Share the same WebDriver instance

        // Perform login with valid credentials
        loginTest.performLogin("student", "Password123");

        // Verify login success
        assertTrue(loginTest.isLoginSuccessful(), "Login was not successful");
    }

    @Test
    public void testInvalidLogin() {
        LoginTest loginTest = new LoginTest();
        loginTest.driver = this.driver; // Share the same WebDriver instance

        // Perform login with invalid credentials
        loginTest.performLogin("invalidUser", "wrongPassword");

        // Verify login failure
        assertFalse(loginTest.isLoginSuccessful(), "Login should not be successful with invalid credentials");
    }

    @Test
    public void testBlankUsernamePassword() {
        LoginTest loginTest = new LoginTest();
        loginTest.driver = this.driver;

        // Perform login with blank username and password
        loginTest.performLogin("", "");

        // Verify that login fails and an error message is displayed
        assertFalse(loginTest.isLoginSuccessful(), "Login should not be successful with blank credentials");
    }

    @Test
    public void testSQLInjection() {
        LoginTest loginTest = new LoginTest();
        loginTest.driver = this.driver;

        // Attempt login using SQL injection in the username field
        loginTest.performLogin("' OR '1'='1", "anyPassword");

        // Verify that login fails
        assertFalse(loginTest.isLoginSuccessful(), "Login should not be successful with SQL injection input");
    }

    public void performLoginWithDelay(String username, String password) {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Introduce a delay to simulate slow network
        try {
            Thread.sleep(5000); // 5 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        loginButton.click();
    }

}
