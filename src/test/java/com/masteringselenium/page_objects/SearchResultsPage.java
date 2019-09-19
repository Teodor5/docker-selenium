package com.masteringselenium.page_objects;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Slf4j
public class SearchResultsPage extends BasePage{
    private final WebDriver webdriver;

    public SearchResultsPage() {
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(css = "#res .g")
    private List<WebElement> results;
    @FindBy(css = "div > div > div.r > a:nth-child(1)")
    private WebElement linkFromSearch;
    @FindBy(css = "#nav > tbody > tr > td > a")
    private List<WebElement> paginationButtons;

    @Step
    public List<WebElement> getResults() {
        log.info("log: get results");

        return results;
    }

    @Step
    public String getLinkByIndex(List<WebElement> results, int pageIndex){
        return results.get(pageIndex).findElement(By.cssSelector("div > div > div.r > a:nth-child(1)")).getAttribute("href");
    }

    @Step
    public SearchResultsPage openPageByPaginator(int paginatorIndex){
        driver.get(paginationButtons.get(paginatorIndex).getAttribute("href"));
        new WebDriverWait(driver, 8).until(visibilityOfElementLocated(By.cssSelector("#res .g")));
        return PageFactory.initElements(driver, SearchResultsPage.class);
    }

    @Step
    public  Boolean isSearchResultContainsDomain(List<WebElement> results, String searchDomain) {
        for (WebElement element : results) {
            System.out.println(element.findElement(By.cssSelector("div > div > div.r > a:nth-child(1)")).getAttribute("href"));

            if(element.findElement(By.cssSelector("div > div > div.r > a:nth-child(1)")).getAttribute("href").contains(searchDomain)){
                return true;
            }
        }
        return false;
    }


}
