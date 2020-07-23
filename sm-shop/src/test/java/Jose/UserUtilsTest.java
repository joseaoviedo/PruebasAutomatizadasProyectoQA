//Jose Oviedo - 2 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.core.model.user.Group;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.utils.UserUtils;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserUtilsTest extends ServicesTestSupport {
    @MockBean
    User mockUser;

    @Autowired
    ApplicationContext context;

    @Test
    public void testUserInGroupTrue(){
        List<Group> grupos = new ArrayList<>();
        Group g1 = new Group();
        g1.setGroupName("Test1");
        grupos.add(g1);
        Group g2 = new Group();
        g2.setGroupName("Test2");
        grupos.add(g2);
        Group g3 = new Group();
        g3.setGroupName("Test3");
        grupos.add(g3);
        Mockito.when(mockUser.getGroups()).thenReturn(grupos);
        boolean flag = UserUtils.userInGroup(mockUser, "Test3");
        assertTrue(flag);
    }

    @Test
    public void testUserInGroupFalse(){
        List<Group> grupos = new ArrayList<>();
        Group g1 = new Group();
        g1.setGroupName("Test1");
        grupos.add(g1);
        Group g2 = new Group();
        g2.setGroupName("Test2");
        grupos.add(g2);
        Group g3 = new Group();
        g3.setGroupName("Test3");
        grupos.add(g3);
        Mockito.when(mockUser.getGroups()).thenReturn(grupos);
        boolean flag = UserUtils.userInGroup(mockUser, "Test4");
        assertFalse(flag);
    }
}
