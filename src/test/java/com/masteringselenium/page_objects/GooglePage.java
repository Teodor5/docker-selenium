package com.masteringselenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GooglePage extends BasePage {
    private WebElement q;
    private final WebDriver webdriver;

    public GooglePage() {
        this.webdriver = driver;
    }

    public SearchResultsPage searchFor(String text) {
        q.sendKeys(text);
        q.submit();
        new WebDriverWait(webdriver, 8).until(visibilityOfElementLocated(By.cssSelector("#res .g")));
        return PageFactory.initElements(webdriver, SearchResultsPage.class);
    }
}