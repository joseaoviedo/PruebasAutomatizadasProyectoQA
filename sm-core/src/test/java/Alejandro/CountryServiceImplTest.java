package Alejandro;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.country.CountryServiceImpl;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.country.CountryDescription;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.test.configuration.ConfigurationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
//4 pruebas de la clase CountryServiceImpl
public class CountryServiceImplTest {

    @Inject
    CountryServiceImpl countryService;

    @Inject
    LanguageService languageService;


    @Test
    public void addCountryDescriptionTest() throws ServiceException {

        Language en = new Language();
        en.setCode("en");
        languageService.save(en);

        Country us = new Country();
        us.setIsoCode("US");
        countryService.save(us);

        CountryDescription usDes = new CountryDescription();
        usDes.setLanguage(en);
        usDes.setCountry(us);
        usDes.setName("United States");

        countryService.addCountryDescription(us,usDes);

        assertEquals(us.getDescriptions().size(),1);

    }

    @Test
    public void getByCodeTest() throws ServiceException {

        Language en = new Language();
        en.setCode("en");
        languageService.save(en);

        Country us = new Country();
        us.setIsoCode("US");

        CountryDescription us_en = new CountryDescription();
        us_en.setLanguage(en);
        us_en.setCountry(us);
        us_en.setName("United States");

        us.getDescriptions().add(us_en);

        countryService.save(us);

        Country c = countryService.getByCode("US");



        assertEquals(c.getIsoCode(),"US");

    }

    @Test
    public void getCountriesTest() throws ServiceException {
        Language en = new Language();
        en.setCode("en");
        languageService.save(en);

        Country us = new Country();
        us.setIsoCode("US");

        CountryDescription us_en = new CountryDescription();
        us_en.setLanguage(en);
        us_en.setCountry(us);
        us_en.setName("United States");

        us.getDescriptions().add(us_en);
        countryService.save(us);
        List<Country> countries = countryService.getCountries(en);

        assertEquals(countries.size(),1);
    }

    @Test
    public void getCountriesMapTest() throws ServiceException {
        Language en = new Language();
        en.setCode("en");
        languageService.save(en);

        Country us = new Country();
        us.setIsoCode("US");

        CountryDescription us_en = new CountryDescription();
        us_en.setLanguage(en);
        us_en.setCountry(us);
        us_en.setName("United States");

        us.getDescriptions().add(us_en);
        countryService.save(us);
        Map<String,Country> countries = countryService.getCountriesMap(en);

        assertEquals(countries.size(),1);

    }
}
