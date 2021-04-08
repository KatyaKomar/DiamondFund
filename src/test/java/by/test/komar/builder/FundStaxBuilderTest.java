package by.test.komar.builder;

import by.task.komar.builder.AbstractFundBuilder;
import by.task.komar.exception.FundException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static by.task.komar.builder.FundBuilderFactory.createFundBuilder;

public class FundStaxBuilderTest {
    AbstractFundBuilder builder;

    @Test(expectedExceptions = FundException.class)
    public void testFundException() throws FundException {
        String type = "STAX";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("");
    }


    @Test
    public void testParsingMinerals() throws FundException {
        String[] expectedNames = new String[]{"Russia", "China", "Brazil", "USA", "Australia", "Brazil", "Russia", "Germany", "USA", "Russia", "Colombia", "USA", "Brazil", "Thailand", "India", "China"};
        String type = "STAX";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("file/fund.xml");
        Object[] actualColors = builder.getMinerals().stream().map(i -> i.getOrigin()).toArray();
        Assert.assertEqualsNoOrder(expectedNames, actualColors);
    }
}