package MasterExampleTestPackage;

import Utils.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MasterExampleTestClass extends Base {

    @Test
    public void Test(){
        myLib.GoToWebsite("http://musabaytechnologies.com/");
        Assert.assertTrue(false);
    }
}
