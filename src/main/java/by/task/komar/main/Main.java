package by.task.komar.main;


import by.task.komar.parser.FundDomBuilder;
import by.task.komar.parser.FundSaxBuilder;
import by.task.komar.parser.FundStaxBuilder;


public class Main {
    public static void main(String[] args) {
        FundDomBuilder builderDom = new FundDomBuilder();
        builderDom.buildSetMinerals("file/fund.xml");
        System.out.println(builderDom.getMinerals());

        FundSaxBuilder builderSax = new FundSaxBuilder();
        builderSax.buildSetMinerals("file/fund.xml");
        System.out.println(builderSax.getMinerals());

        FundStaxBuilder builderStax = new FundStaxBuilder();
        builderStax.buildSetMinerals("file/fund.xml");
        System.out.println(builderStax.getMinerals());

    }
}
