package by.test.komar.builder;

import by.task.komar.builder.AbstractFundBuilder;
import by.task.komar.exception.FundException;
import org.testng.Assert;
import org.testng.annotations.Test;

import static by.task.komar.builder.FundBuilderFactory.createFundBuilder;

public class FundSaxBuilderTest {
    private AbstractFundBuilder builder;

    @Test(expectedExceptions = FundException.class)
    public void testFundException() throws FundException {
        String type = "SAX";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("");
    }

    @Test
    public void testBuildSetMinerals() throws FundException {
        String[] expectedNames = new String[]{"2017-05-02", "2014-06-10", "2021-01-10", "2020-04-03", "2019-03-26", "2019-05-04", "2018-02-04", "2015-01-15", "2019-09-10", "2016-07-24", "2021-02-12", "2017-10-23", "2020-04-03", "2016-02-29", "2020-11-11", "2011-02-05"};
        String type = "SAX";
        builder = createFundBuilder(type);
        builder.buildSetMinerals("src\\main\\resources\\file\\fund.xml");
        Object[] actualColors = builder.getMinerals().stream().map(i -> i.getDate().toString()).toArray();
        Assert.assertEqualsNoOrder(expectedNames, actualColors);
    }
}