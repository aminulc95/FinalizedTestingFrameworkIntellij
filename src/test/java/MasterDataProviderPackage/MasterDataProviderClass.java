package MasterDataProviderPackage;

import Utils.Base;
import Utils.ExcelReadWrite;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MasterDataProviderClass extends Base {

    String Site = "https://www.change.org/p/fairfax-county-public-school-make-halal-and-kosher-foods-available-in-fairfax-school-cafeterias?recruiter=1043047827&utm_source=share_petition&utm_campaign=psf_combo_share_initial&utm_medium=whatsapp&recruited_by_id=519ac380-5034-11ea-b669-0bf9c7047fa2&utm_content=washarecopy_20284488_en-US%3Av10";

    @DataProvider(name = "UserData")
    public static Object[][] UserData() {
        ExcelReadWrite dataReader = new ExcelReadWrite();
        return  dataReader.getExcelData(workingDir + "\\src\\main\\resources\\Namesxls.xls", "Sheet1");
    }

    public void fillUpperPortion(String FName, String LName){
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(FName);
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(LName);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(FName + "." + LName + "." + myLib.getCurrentTime() + "@gmail.com");
    }
    public void checkForVINAndExecute(String City) {
        try {
            By a = By.xpath("//div[@class='box mtn mbn xs-mbs box-basic bg-brighter']");
            driver.findElement(a).click();
            handleAddress(City);
        } catch (Exception e) {
            handleAddress(City);
        }
    }
    public void handleAddress (String City){
        driver.findElement(By.xpath("//*[@id='city']")).clear();
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,
                City);
        Select drpdwn = new Select(driver.findElement(By.xpath("//select[@id='stateCode']")));
        drpdwn.selectByVisibleText("VA");
        driver.findElement(By.xpath("//*[@id='postalCode']")).clear();
        driver.findElement(By.xpath("//*[@id='postalCode']")).sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,
                Keys.BACK_SPACE,Keys.BACK_SPACE,"22192");
        driver.findElement(By.xpath("//button[@class='btn btn-big btn-full btn-action']")).click();
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[4]/div/div/div/div/div[2]/div[1]/div/div/div/div/div"));
        } catch (Exception e) {
            System.out.println("No Good");
        }
    }

    @Test(dataProvider = "UserData")
    public void RunTest(String FName, String LName, String City) {
        driver.get(Site);
        fillUpperPortion(FName,LName);
        checkForVINAndExecute(City);
    }
}
