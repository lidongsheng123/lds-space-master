package com.bmsoft.auth;


import com.bmsoft.auth.config.BaseServiceTest;
import com.bmsoft.auth.dao.ClientSecretDAO;
import com.bmsoft.auth.dto.ApiClientDTO;
import com.bmsoft.auth.entity.ClientSecret;
import com.bmsoft.auth.service.ClientSecretService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author keets
 * @date 2016/12/5
 */

@RunWith(SpringRunner.class)
@SpringBootTest("classpath:application-test.yml")
@DirtiesContext
//@ContextConfiguration(locations = {"classpath:application-test.yml"})
public class AuthApplicationTest extends BaseServiceTest {

    @Mock
    private ClientSecretDAO clientSecretDao;

    @Mock
    private ClientSecretService clientSecretService;

    @Before
    public void setUp() {
        ApiClientDTO mockClient = new ApiClientDTO();
        mockClient.setClientId("test");
        mockClient.setClientSecret("test");
        mockClient.setPurpose("test..");
        ClientSecret clientSecret = new ClientSecret.ClientSecretBuilder()
                .withTenantId(UUID.randomUUID())
                .build();
        when(clientSecretService.getClientSecretByClientId("test")).thenReturn(mockClient);

//        when(clientSecretDao.get(clientSecret)).thenReturn()
    }

    @Test
    public void getClientSecretByClientIdTest() {
        ApiClientDTO apiClientDTO = clientSecretService.getClientSecretByClientId("test");
        Assert.assertEquals(apiClientDTO.getClientSecret(), "test");
    }


}