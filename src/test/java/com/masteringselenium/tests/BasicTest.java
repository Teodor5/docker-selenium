package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.GooglePage;
import com.masteringselenium.page_objects.SearchResultsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class BasicTest extends DriverBase {

    @DataProvider(name = "data-provider")
    public Object[] dataProviderMethod() {
        return new Object[][]{{"automation"}};
    }

    @Test(dataProvider = "data-provider")
    public void searchAutomationInTitleTest(String text) {
        GooglePage page = new GooglePage();
        SearchResultsPage resultsPage = page.open("https://www.google.com")
                .searchFor(text);
        page.open(resultsPage.getLinkByIndex(resultsPage.getResults(), 0));
        assertThat(page.getTitle().contains("automation"));
    }
   /* @Test
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
    */
}
