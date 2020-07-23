//Jose Oviedo - 2 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.utils.LanguageUtils;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LanguageUtilsTest extends ServicesTestSupport {
    @Autowired
    ApplicationContext context;

    LanguageUtils lu;

    @Test
    public void getServiceLanguageValid(){
        lu = context.getBean(LanguageUtils.class);
        String lang = "fr";
        Language res = lu.getServiceLanguage(lang);
        boolean flag = lang.equals(res.getCode());
        assertTrue(flag);
    }

    /*
        Debe devolver el lenguaje por defecto(Inglés)
     */
    @Test
    public void getServiceLanguageInvalid(){
        lu = context.getBean(LanguageUtils.class);
        String lang = "INVALID CODE";
        Language res = lu.getServiceLanguage(lang);
        String temp  = "en";
        boolean flag = temp.equals(res.getCode());
        assertTrue(flag);
    }
}
