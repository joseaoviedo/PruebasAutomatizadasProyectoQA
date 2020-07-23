//Jose Oviedo - 3 casos
package com.salesmanager.test.Jose;

import com.salesmanager.core.business.modules.utils.EncryptionImpl;
import com.salesmanager.test.configuration.ConfigurationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
public class EncryptionImplTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void encrypTest() throws Exception {
        EncryptionImpl enc = context.getBean(EncryptionImpl.class);
        String test = "This is a test string";
        String encrypted = enc.encrypt(test);
        boolean flag = test.equals(encrypted);
        assertFalse(flag);
    }

    @Test
    public void decryptWithValidString() throws Exception {
        EncryptionImpl enc = context.getBean(EncryptionImpl.class);
        String test = "This is a test string";
        String encrypted = enc.encrypt(test);
        String decrypted = enc.decrypt(encrypted);
        boolean flag = decrypted.equals(test);
        assertTrue(flag);
    }

    @Test(expected =  Exception.class)
    public void decryptWithInvalidString() throws Exception{
        EncryptionImpl enc = context.getBean(EncryptionImpl.class);
        String test = "";
        String decrypted = enc.decrypt(test);
    }
}
