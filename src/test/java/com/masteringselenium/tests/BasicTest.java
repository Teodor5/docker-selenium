package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.GooglePage;
import com.masteringselenium.page_objects.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class BasicTest extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void searchAutomationinTitleTest() {
        driver.get("https://www.google.com");
        GooglePage page = PageFactory.initElements(driver, GooglePage.class);
        SearchResultsPage resultsPage = page.searchFor("automation");
        driver.get(resultsPage.getLinkByIndex(resultsPage.getResults(), 0));
        assertThat(driver.getTitle().contains("automation"));
    }
//Незакінчене, неправильна перевірка
    @Test
    public void searchDomainTest() {
        driver.get("https://www.google.com");
        GooglePage page = PageFactory.initElements(driver, GooglePage.class);
        SearchResultsPage resultsPage = page.searchFor("automation");
        List<WebElement> results = resultsPage.getResults();
        for(int i = 2; i <= 5;i++){
            results.addAll(resultsPage.openPageByPaginator(i).getResults());

        }
        assertThat(resultsPage.isSearchResultContainsDomain(results,"testautomationday.com").equals(true));
    }




}
