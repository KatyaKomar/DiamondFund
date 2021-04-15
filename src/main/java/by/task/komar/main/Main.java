package by.task.komar.main;


import by.task.komar.builder.AbstractFundBuilder;
import by.task.komar.exception.FundException;
import by.task.komar.validator.XmlValidator;

import static by.task.komar.builder.FundBuilderFactory.createFundBuilder;


public class Main {
    public static void main(String[] args) throws FundException {
        XmlValidator validator = new XmlValidator();
        if (validator.validate("file/fund.xml")) {
            String type = "STAX";
            AbstractFundBuilder builder = createFundBuilder(type);
            builder.buildSetMinerals("file/fund.xml");
            System.out.println(builder.getMinerals());
            type = "SAX";
            builder = createFundBuilder(type);
            builder.buildSetMinerals("file/fund.xml");
            System.out.println(builder.getMinerals());
            type = "DOM";
            builder = createFundBuilder(type);
            builder.buildSetMinerals("file/fund.xml");
            System.out.println(builder.getMinerals());
        }
    }
}
