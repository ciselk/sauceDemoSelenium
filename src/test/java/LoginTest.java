import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @DataProvider(name = "invalidLoginDataProvider")
    public Object[][] invalidLogin(){
        return new Object[][] {
                {"locked_out_user","secret_sauce"},
        };
    }

    @Test(priority = 1,dataProvider = "invalidLoginDataProvider")
    public void testInvalidLogin (String username,String password) {

        WebElement userName = driver.findElement(By.id("user-name"));
        WebElement passWord = driver.findElement(By.id("password"));
        userName.sendKeys(username);
        passWord.sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='error-message-container error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.");

    }

    @DataProvider(name = "validLoginDataProvider")
    public Object[][] validLogin(){
        return new Object[][] {
                {"standard_user","secret_sauce"},
                {"error_user","secret_sauce"}
        };
    }
    @Test(priority = 2,dataProvider = "validLoginDataProvider",description = "Verify that a user can login to application using the correct credentials")
    public void testValidLogin (String username,String password) {

        WebElement userName = driver.findElement(By.id("user-name"));
        WebElement passWord = driver.findElement(By.id("password"));
        userName.sendKeys(username);
        passWord.sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-test='title']")).getText(), "Products");
    }
}
