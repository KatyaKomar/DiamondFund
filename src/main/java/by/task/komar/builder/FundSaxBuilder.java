package by.task.komar.builder;

import by.task.komar.exception.FundException;
import by.task.komar.handler.FundErrorHandler;
import by.task.komar.handler.FundHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class FundSaxBuilder extends AbstractFundBuilder {
    private static Logger logger = LogManager.getLogger();
    private FundHandler handler = new FundHandler();
    private XMLReader reader;

    public FundSaxBuilder() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException ex) {
            logger.log(Level.ERROR, "Error in Sax: " + ex.getMessage());

        }
        reader.setErrorHandler(new FundErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetMinerals(String filename) throws FundException {
        try {
            reader.parse(filename);
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Error in Sax, check your filename: " + filename);
            throw new FundException("Error in Sax, check your filename: " + filename);
        } catch (SAXException ex) {
            logger.log(Level.ERROR, "Error in Sax: " + ex.getMessage());
            throw new FundException("Error in Sax: " + ex.getMessage());
        }
        minerals = handler.getMinerals();
        logger.log(Level.INFO, "Minerals from sax builder are:\n" + minerals);
    }
}
