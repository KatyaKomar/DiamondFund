package by.task.komar.builder;

public class FundBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private FundBuilderFactory() {
    }

    public static AbstractFundBuilder createFundBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FundDomBuilder();
            case STAX:
                return new FundStaxBuilder();
            case SAX:
                return new FundSaxBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }
}
