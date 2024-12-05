package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest extends BaseTest {

    public void performLogin(String username, String password) {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Logged In Successfully']")));
            WebElement welcomeMessage = driver.findElement(By.xpath("//h1[normalize-space()='Logged In Successfully']"));
            return welcomeMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
