package by.test.komar.builder;

import by.task.komar.builder.AbstractFundBuilder;
import by.task.komar.exception.FundException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static by.task.komar.builder.FundBuilderFactory.createFundBuilder;

public class FundDomBuilderTest {
    AbstractFundBuilder builder;

    @Test(expectedExceptions = FundException.class)
    public void testFundException() throws FundException {
        String type = "DOM";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("");
    }


    @Test
    public void testParsingMinerals() throws FundException {
        String[] expectedNames = new String[]{"purple", "pink", "blue", "yellow", "pink", "red", "colorless", "purple", "brown", "green", "green", "light blue", "purple", "red", "blue", "pink"};
        String type = "DOM";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("src\\main\\resources\\file\\fund.xml");
        Object[] actualColors = builder.getMinerals().stream().map(i -> i.getParameters().getColor()).toArray();
        Assert.assertEqualsNoOrder(expectedNames, actualColors);
    }
}