package com.masteringselenium.page_objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import lombok.extern.slf4j.Slf4j;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Slf4j
public class GooglePage extends BasePage {
    private WebElement q;
    private final WebDriver webdriver;

    public GooglePage() {
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    public GooglePage open(String url){
        webdriver.get(url);
        return this;
    }

    public String getTitle(){
       return webdriver.getTitle();
    }
    @Step
    public SearchResultsPage searchFor(String text) {
        log.info("About to search text [ " + text + " ]");
        q.sendKeys(text);
        q.submit();
        new WebDriverWait(webdriver, 8).until(visibilityOfElementLocated(By.cssSelector("#res .g")));
        return new SearchResultsPage();
    }
}