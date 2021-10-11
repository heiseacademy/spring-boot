package de.trion.training.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.VncRecordingContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BrowserTest {
   @LocalServerPort
   private int port;

   @Container
   private BrowserWebDriverContainer<?> container = new BrowserWebDriverContainer<>()
         .withCapabilities(new FirefoxOptions())
         .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL,
               new File("target"),
               VncRecordingContainer.VncRecordingFormat.MP4);

   @Test
   public void smokeTest() throws Exception {
      RemoteWebDriver webDriver = container.getWebDriver();
      webDriver.get("http://%s:%s/".formatted("172.17.0.1", port)); // win/mac: host.docker.internal

      WebElement greeting = webDriver.findElementByTagName("h1");
      assertThat(greeting.getText()).isEqualTo("Hello Model and View!");

      webDriver.findElementByPartialLinkText("Trainings").click();

      assertThat(webDriver.findElementByTagName("h1").getText()).isEqualTo("Alle Trainings");

      webDriver.findElementByPartialLinkText("Weiter").click();
      webDriver.findElementByPartialLinkText("Weiter").click();
      webDriver.findElementByPartialLinkText("Weiter").click();

      TimeUnit.SECONDS.sleep(3);

      assertThat(webDriver.findElementByPartialLinkText("Weiter")
            .findElement(By.xpath("./.."))
            .getAttribute("class")).contains("disabled");

      List<WebElement> trainings = webDriver.findElementByTagName("table")
            .findElements(By.tagName("a"));

      trainings.get(trainings.size() -1 ).click();

      assertThat(webDriver.findElementByTagName("h1").getText()).startsWith("Training:");

      TimeUnit.SECONDS.sleep(2);

   }
}
