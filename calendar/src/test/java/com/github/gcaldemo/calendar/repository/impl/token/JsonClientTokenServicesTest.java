package com.github.gcaldemo.calendar.repository.impl.token;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.File;

import static org.junit.Assert.*;

public class JsonClientTokenServicesTest {
    private JsonClientTokenServices jsonClientTokenServices;
    private File accessTokenFile;

    @Before
    public void setUp() throws Exception {
        this.accessTokenFile = new File(Files.createTempDir(), "test_token.json");
        this.jsonClientTokenServices = new JsonClientTokenServices(accessTokenFile);
    }

    @After
    public void tearDown() {
        this.accessTokenFile.delete();
    }

    @Test
    public void testWrite() {
        assertFalse(accessTokenFile.exists());
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken("blabla");
        jsonClientTokenServices.saveAccessToken(null, null, token);
        assertTrue(accessTokenFile.exists());
    }

    @Test
    public void testRead() {
        DefaultOAuth2AccessToken writtenToken = new DefaultOAuth2AccessToken("blabla");
        jsonClientTokenServices.saveAccessToken(null, null, writtenToken);

        OAuth2AccessToken readToken = jsonClientTokenServices.getAccessToken(null, null);

        assertEquals("blabla", readToken.getValue());
    }

    @Test
    public void testRemove() {
        DefaultOAuth2AccessToken writtenToken = new DefaultOAuth2AccessToken("blabla");
        jsonClientTokenServices.saveAccessToken(null, null, writtenToken);

        Assert.assertTrue(accessTokenFile.exists());
        jsonClientTokenServices.removeAccessToken(null, null);
        assertNull(jsonClientTokenServices.getAccessToken(null, null));
        assertFalse(accessTokenFile.exists());
    }

}
