package by.task.komar.builder;

import by.task.komar.exception.FundException;
import by.task.komar.fund.*;
import by.task.komar.handler.FundXmlTag;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class FundStaxBuilder extends AbstractFundBuilder {
    private static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public FundStaxBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetMinerals(String filename) throws FundException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FundXmlTag.PRECIOUS.getTitle())) {
                        Precious precious = buildPrecious(reader);
                        minerals.add(precious);
                    } else if (name.equals(FundXmlTag.SEMIPRECIOUS.getTitle())) {
                        Semiprecious semiprecious = buildSemiprecious(reader);
                        minerals.add(semiprecious);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException ex) {
            logger.log(Level.ERROR, "Error in Stax: " + ex.getMessage());
            throw new FundException("Error in Stax: " + ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Error in Stax, check your filename: " + filename);
            throw new FundException("Error in Stax, check your filename: " + filename);
        }
        logger.log(Level.INFO, "Minerals from stax builder are:\n" + minerals);
    }

    private Precious buildPrecious(XMLStreamReader reader) throws XMLStreamException {
        Precious precious = new Precious();
        Name name_of_precious = Name.valueOf(reader.getAttributeValue(null, FundXmlTag.NAME.getTitle()).toUpperCase());
        precious.setName(name_of_precious);
        build(reader, precious);
        return precious;
    }

    private Semiprecious buildSemiprecious(XMLStreamReader reader) throws XMLStreamException {
        Semiprecious semiprecious = new Semiprecious();
        semiprecious.setName(reader.getAttributeValue(null, FundXmlTag.NAME.getTitle()));
        build(reader, semiprecious);
        return semiprecious;
    }


    private void build(XMLStreamReader reader, Mineral mineral) throws XMLStreamException {
        mineral.setOrigin(reader.getAttributeValue(null, FundXmlTag.ORIGIN.getTitle()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FundXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case VISUAL_PARAMETERS:
                            mineral.setParameters(getXMLParameters(reader));
                            break;
                        case VALUE:
                            mineral.setValue(Double.parseDouble(getXMLText(reader)));
                            break;
                        case DATE_OF_PROCESSING:
                            mineral.setDate(LocalDate.parse(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FundXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == FundXmlTag.PRECIOUS ||
                            FundXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == FundXmlTag.SEMIPRECIOUS) {
                        return;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <precious>");
    }

    private VisualParameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters parameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FundXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case COLOR:
                            parameters.setColor(getXMLText(reader));
                            break;
                        case TRANSPARENCY:
                            parameters.setTransparency(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CUT:
                            parameters.setCut(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FundXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == FundXmlTag.VISUAL_PARAMETERS) {
                        return parameters;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
