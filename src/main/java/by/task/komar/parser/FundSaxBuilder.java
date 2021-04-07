package by.task.komar.parser;

import by.task.komar.fund.Mineral;
import by.task.komar.handler.FundErrorHandler;
import by.task.komar.handler.FundHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FundSaxBuilder {
    private Set<Mineral> minerals;
    private FundHandler handler = new FundHandler();
    private XMLReader reader;

    public FundSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException ex){
            ex.printStackTrace();
        }
        //reader.setContentHandler(new ConsoleFundHandler());
        reader.setErrorHandler(new FundErrorHandler());
        reader.setContentHandler(handler);
    }
    public Set<Mineral> getMinerals(){
        return minerals;
    }

    public void buildSetMinerals(String filename){
        try{
            reader.parse(filename);
        }catch (IOException | SAXException ex){
            ex.printStackTrace();
        }
        minerals = handler.getMinerals();
    }
}
