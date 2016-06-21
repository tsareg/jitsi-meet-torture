/*
 * Copyright @ 2016 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jitsi.meet.test;

import io.appium.java_client.ios.IOSDriver;
import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jitsi.meet.test.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Sample Appium test.
 *
 * @author Kostiantyn Tsaregradskyi
 */
public class AppiumSampleTest
    extends TestCase
{
    /**
     * Constructs test.
     * @param name the method name for the test.
     */
    public AppiumSampleTest(String name)
    {
        super(name);
    }

    /**
     * Orders the tests.
     * @return the suite with order tests.
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        suite.addTest(new AppiumSampleTest("testIosJoinRoom"));

        return suite;
    }

    public void testIosJoinRoom()
    {
        System.err.println("Start testIosJoinRoom");

        WebDriver iosDriver = ConferenceFixture.getIosParticipant();

        WebElement roomNameTextField = iosDriver.findElement(By.xpath("//UIATextField[1]"));
        roomNameTextField.click();
        roomNameTextField.sendKeys(ConferenceFixture.currentRoomName + "\n");

        iosDriver.findElement(By.xpath("//*[@name=\"  JOIN \"]")).click();

        (new WebDriverWait(iosDriver, 10)).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@name=\"Conference screen\"]")));

        ConferenceFixture.closeIosParticipant();
    }
}
