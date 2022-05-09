package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://www.emag.ro/")
public class DictionaryPage extends PageObject {

    @FindBy(name="query")
    private WebElementFacade searchTerms;

    @FindBy(className="searchbox-submit-button")
    private WebElementFacade lookupButton;

    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        lookupButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[2]"));
        return definitionList.findElements(By.className("js-product-url")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }
}