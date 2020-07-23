package Alejandro;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
//5 pruebas a la clase LanguageServiceImpl
public class LanguageServiceImplTest  {

    @Inject
    LanguageService languageService;


    @Test
    public void toLocaleTestG(){

        com.salesmanager.core.model.reference.language.Language lan = new Language();
        lan.setCode("en");
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.country.Country con = new Country();
        con.setIsoCode("CA");
        str.setCountry(con);

        Locale lo = languageService.toLocale(lan,str);

        assertEquals(lo.getCountry(),"US");
    }

    @Test
    public void toLocaleTestB(){

        com.salesmanager.core.model.reference.language.Language lan = new Language();
        lan.setCode("fr");

        Locale lo = languageService.toLocale(lan,null);

        assertEquals(lo.getLanguage(),"fr");
    }

    @Test
    public void toLocaleTestG2(){

        com.salesmanager.core.model.reference.language.Language lan = new Language();
        lan.setCode("en");
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.country.Country con = new Country();
        con.setIsoCode("UK");
        str.setCountry(con);

        Locale lo = languageService.toLocale(lan,str);

        assertEquals(lo.getCountry(),"UK");
    }
    @Test
    public void toLanguageTest() throws ServiceException {
        Language fr = new Language();
        fr.setCode("fr");
        fr.setSortOrder(0);

        languageService.save(fr);

        Locale loca = new Locale("fr");

        Language lan = new Language();

        lan = languageService.toLanguage(loca);

        assertEquals(lan.getCode(),"fr");
    }

    @Test
    public void getLanguagesTestG() throws ServiceException {
        Language en = new Language();
        en.setCode("en");
        en.setSortOrder(0);
        Language fr = new Language();
        fr.setCode("fr");
        fr.setSortOrder(0);
        languageService.save(en);
        languageService.save(fr);

        List<Language> len = languageService.getLanguages();

        assertEquals(len.get(0).getCode(),"en");
    }

}
