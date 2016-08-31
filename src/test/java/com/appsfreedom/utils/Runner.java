package com.appsfreedom.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.fm.pages.Menu;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class Runner {

	@Test(retryAnalyzer = Retry.class)
    public void testGenX() throws Exception {
		
		Menu.PlatformConfig.Subscription.getTo();
       // Assert.assertEquals("james", "JamesFail"); // ListenerTest fails
    }

    /*@Test(retryAnalyzer = Retry.class)
    public void testGenY() {
        Assert.assertEquals("hello", "World"); // ListenerTest fails

    }*/

}



