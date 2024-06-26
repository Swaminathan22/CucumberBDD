package StepDefs;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlayerDetailsSteps {

    WebDriver driver;

    @Given("I launch ESPN Cricinfo website")
    public void i_launch_espn_cricinfo_website() {
        driver = new ChromeDriver();
        driver.get("https://www.espncricinfo.com/");
        driver.manage().window().maximize();
        handleOverlay();
    }

    @When("I navigate to the {string} section under {string}")
    public void i_navigate_to_the_section_under(String section, String menu) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            handleOverlay();
            Assertions.assertThat(driver.findElement(By.cssSelector("a[title='" + menu + "']")).isDisplayed())
                .as("Check if " + menu + " section is displayed").isTrue();
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail(menu + " section is not displayed or could not be found", e);
        }
        WebElement overlayInfoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='wzrk-cancel']")));
        overlayInfoButton.click();
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.cssSelector("a[title='" + menu + "']"))).build().perform();
        driver.findElement(By.xpath("//span[contains(text(),'" + section + "')]")).click();
        handleOverlay();
    }

    @When("I select the player named {string}")
    public void i_select_the_player_named(String playerName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement targetElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'" + playerName + "')]")));

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", targetElement);
        handleOverlay();
        
        driver.findElement(By.xpath("//span[contains(text(),'" + playerName + "')]")).click();
        handleOverlay();
    }

    @Then("I should see the player details and verify them against the expected data")
    public void i_should_see_the_player_details_and_verify_them_against_the_expected_data() throws InterruptedException {
        Thread.sleep(3000);

        List<WebElement> playerDetails = driver.findElements(By.xpath(".//span[@class='ds-text-title-s ds-font-bold ds-text-typo']/p"));

        List<String> myDetails = new ArrayList<String>();
        myDetails.add("Ravichandran Ashwin");
        myDetails.add("September 17, 1986, Madras (now Chennai), Tamil Nadu");
        myDetails.add("37y 284d");
        myDetails.add("Right hand Bat");
        myDetails.add("Right arm Offbreak");
        myDetails.add("Bowling Allrounder");

        for (int i = 0; i < playerDetails.size(); i++) {
            String actualText = playerDetails.get(i).getText();
            String expectedText = myDetails.get(i);
            Assert.assertEquals("Mismatch at index " + i, expectedText, actualText);
        }

        driver.quit();
    }

    public void handleOverlay() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement overlayCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='sprite close']")));
            overlayCloseButton.click();
            System.out.println("Overlay closed successfully.");
        } catch (Exception e) {
            System.out.println("Overlay did not appear or was already closed.");
        }
    }
}