package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ActivitiesSearchPage {
    private SelenideElement cityInput = $(byAttribute("name", "city")),
            dropdownInput = $(byAttribute("data-locator", "dropdown-overlay")),
            submitButton = $(byAttribute("data-locator", "Button activities-search-form__submit-button")),
            activityCard = $(byAttribute("data-locator", "search-result-page__activity-card")),
            filterByLocality = $(byAttribute("data-locator", "filter-byLocality")),
            filterBySights = $(byAttribute("data-locator", "filter-bySight"));

    public ActivitiesSearchPage openPage() {
        open("/ru/activities/search");
        return this;
    }

    public ActivitiesSearchPage searchActivitiesCardsByCity(String value) {
        cityInput.setValue(value);
        dropdownInput.$(byText(value)).click();
        submitButton.click();
        return this;
    }

    public void activitiesCardsDisplayed() {
        activityCard.shouldBe(visible);
    }

    public void filterByLocalityContainsValue(List<String> localities) {
        localities.forEach(locality -> filterByLocality.shouldHave(text(locality)));
    }

    public void filterBySightsContainsValue(String sight) {
        filterBySights.shouldHave(text(sight));
    }
}
