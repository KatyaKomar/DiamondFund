package by.task.komar.validator;

import by.task.komar.handler.FundErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static Logger logger = LogManager.getLogger();

    public boolean validate(String xmlFilePath) {
        final String SCHEMA_NAME = "file/fund.xsd";
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(SCHEMA_NAME);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.setErrorHandler(new FundErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException ex) {
            logger.log(Level.ERROR, "File can't validate because: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
