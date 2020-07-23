//Jose Oviedo - 2 casos
package com.salesmanager.test.Jose;
import com.salesmanager.core.business.utils.CreditCardUtils;
import com.salesmanager.test.configuration.ConfigurationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
public class CreditCardUtilsTest {
    @Test
    public void maskCardNumberValidCard() throws Exception {
        String card = "1234123412341234";
        String res = CreditCardUtils.maskCardNumber(card);
        String expected = "1234XXXXXXXXXX1234";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test(expected = Exception.class)
    public void maskCardNumberInvalidCard() throws Exception {
        String card = "1234";
        String res = CreditCardUtils.maskCardNumber(card);
    }
}
