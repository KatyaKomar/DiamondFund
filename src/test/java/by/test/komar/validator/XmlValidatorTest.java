package by.test.komar.validator;

import by.task.komar.validator.XmlValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XmlValidatorTest {

    @Test
    public void testValidate() {
        XmlValidator validator = new XmlValidator();
        boolean isRead = validator.validate("src\\main\\resources\\file\\fund.xml");
        Assert.assertTrue(isRead);
    }
}