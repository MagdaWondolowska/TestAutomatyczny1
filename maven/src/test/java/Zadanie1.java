import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Zadanie1 {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void openBrowser () {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,3);

    }

    @After
    public void closeBrowser () {
        driver.quit();
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }






    @When("user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        WebElement element = driver.findElement(By.id("field-email"));
        element.sendKeys(username);
        element = driver.findElement(By.id("field-password"));
        element.sendKeys(password);
    }

    @And("user clicks on the login button")
    public void userClicksOnTheLoginButton() {
        WebElement element = driver.findElement(By.cssSelector("button[id='submit-login']"));
        element.click();
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        WebElement element = driver.findElement(By.cssSelector("a[class='logout hidden-sm-down']"));
        String currentLoginText = element.getText();
        String expectedLoginText = "Sign out";
        Assert.assertTrue(currentLoginText.contains(expectedLoginText));
    }

    @Given("user is on Your Account Page")
    public void userIsOnYourAccountPage() {
        WebElement element = driver.findElement(By.cssSelector("a[class='logout hidden-sm-down']"));
        String currentLoginText = element.getText();
        String expectedLoginText = "Sign out";
        Assert.assertTrue(currentLoginText.contains(expectedLoginText));

    }

    @When("user clicks on the Addresses button")
    public void userClicksOnTheAddressesButton() {
        WebElement element = driver.findElement(By.xpath("//*[@id='addresses-link']/span"));
        element.click();
    }

    @And("user clicks on Create New Address button")
    public void userClicksOnCreateNewAddressButton() {
        WebElement element = driver.findElement(By.cssSelector("a[data-link-action='add-address']"));
        element.click();
    }

    @And("user fills alias {string} address {string} city {string} zip {string} country {string} phone {string}")
    public void userFillsAliasAddressCityZipCountryPhone(String alias, String address, String city, String zip, String country, String phone) {
        WebElement element = driver.findElement(By.id("field-phone"));
        element.sendKeys(phone);
        element = driver.findElement(By.id("field-alias"));
        element.sendKeys(alias);
        element = driver.findElement(By.id("field-address1"));
        element.sendKeys(address);
        element = driver.findElement(By.id("field-city"));
        element.sendKeys(city);
        element = driver.findElement(By.id("field-postcode"));
        element.sendKeys(zip);


    }

    @And("user clicks on Save button")
    public void userClicksOnSaveButton() {
        WebElement element = driver.findElement(By.cssSelector("button[class='btn btn-primary form-control-submit float-xs-right']"));
        element.click();
    }

    @Then("new address is added")
    public void newAddressIsAdded() {
        WebElement element = driver.findElement(By.xpath("//*[@id='notifications']/div/article/ul/li"));
        String currentText = element.getText();
        String expectedText = "Address successfully added!";
        Assert.assertTrue(currentText.contains(expectedText));
    }


    @And("new address matches {string} address {string} city {string} zip {string} country {string} phone {string}")
    public void newAddressMatchesAddressCityZipCountryPhone(String alias, String address, String city, String zip, String country, String phone) {
        List<WebElement> addressElements = driver.findElements(By.cssSelector("article.address"));
        WebElement lastAddress = addressElements.get(addressElements.size() - 1);
        String lastAddressText = lastAddress.getText();
        Assert.assertTrue(lastAddressText.contains(alias));
        Assert.assertTrue(lastAddressText.contains(address));
        Assert.assertTrue(lastAddressText.contains(city));
        Assert.assertTrue(lastAddressText.contains(zip));
        Assert.assertTrue(lastAddressText.contains(phone));

    }

    @When("user deletes new address")
    public void userDeletesNewAddress() {
        List<WebElement> addressElements = driver.findElements(By.cssSelector("article.address"));
        WebElement lastAddressElement = addressElements.get(addressElements.size() - 1);
        WebElement deleteButton = lastAddressElement.findElement(By.cssSelector("a[data-link-action='delete-address']"));
        deleteButton.click();
    }

    @Then("new address is deleted")
    public void newAddressIsDeleted() {
        WebElement element = driver.findElement(By.xpath("//*[@id='notifications']/div/article/ul/li"));
        String currentText = element.getText();
        String expectedText = "Address successfully deleted!";
        Assert.assertTrue(currentText.contains(expectedText));
    }


}