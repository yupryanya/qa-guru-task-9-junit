package guru.qa.tests;

import guru.qa.pages.ActivitiesSearchPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

public class ActivitiesSearchParametrizedTests extends TestBase {
    ActivitiesSearchPage activitiesSearchPage = new ActivitiesSearchPage();

    @ValueSource(strings = {
            "Москва",
            "Казань",
            "Тульская область"
    })
    @ParameterizedTest(name = "При поиске по параметру {0} отображаются карточки экскурсий")
    @Tag("smoke")
    void searchWithValidValueShouldHaveResult(String cityname) {
        activitiesSearchPage.openPage()
                .searchActivitiesCardsByCity(cityname)
                .activitiesCardsDisplayed();
    }

    @CsvSource({
            "Санкт-Петербург, Петропавловская крепость",
            "Москва,          Кремль"
    })
    @ParameterizedTest(name = "При поиске по городу {0} в фильтре отображается достопримечательность {1}")
    @Tag("smoke")
    void whenSearchWithCitySightsFilterDisplayed(String city, String sight) {
        activitiesSearchPage.openPage()
                .searchActivitiesCardsByCity(city)
                .filterBySightsContainsValue(sight);
    }

    static Stream<Arguments> whenSearchWithRegionLocalityFilterDisplayed() {
        return Stream.of(
                Arguments.of("Калининградская область", List.of("Калининград", "Зеленоградск", "Светлогорск")),
                Arguments.of("Республика Татарстан", List.of("Казань", "Свияжск", "Болгар"))
        );
    }

    @MethodSource("whenSearchWithRegionLocalityFilterDisplayed")
    @ParameterizedTest(name = "При поиске по региону {0} в фильтре отображаются города")
    @Tag("smoke")
    void whenSearchWithRegionLocalityFilterDisplayed(String region, List<String> locations) {
        activitiesSearchPage.openPage()
                .searchActivitiesCardsByCity(region)
                .filterByLocalityContainsValue(locations);
    }
}
