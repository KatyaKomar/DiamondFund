package by.task.komar.parser;

import by.task.komar.fund.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FundDomBuilder {
    private Set<Mineral> minerals;
    private DocumentBuilder documentBuilder;

    public FundDomBuilder() {
        minerals = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public Set<Mineral> getMinerals() {
        return minerals;
    }

    public void buildSetMinerals(String filename) {
        Document doc;
        try {
            doc = documentBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList semipreciousList = root.getElementsByTagName("semiprecious");
            NodeList preciousList = root.getElementsByTagName("precious");
            for (int i = 0; i < semipreciousList.getLength(); i++) {
                Element mineralElement = (Element) semipreciousList.item(i);
                Semiprecious mineral = buildSemiprecious(mineralElement);
                minerals.add(mineral);
            }
            for (int i = 0; i < preciousList.getLength(); i++) {
                Element mineralElement = (Element) preciousList.item(i);
                Precious mineral = buildPrecious(mineralElement);
                minerals.add(mineral);
            }
        } catch (IOException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    private Semiprecious buildSemiprecious(Element element) {
        Semiprecious semiprecious = new Semiprecious();
        semiprecious.setName(element.getAttribute("name"));
        build(element, semiprecious);
        return semiprecious;
    }

    private Precious buildPrecious(Element element) {
        Precious precious = new Precious();
        Name name = Name.valueOf(element.getAttribute("name").toUpperCase());
        precious.setName(name);
        build(element, precious);
        return precious;
    }

    private void build(Element element, Mineral mineral){
        mineral.setOrigin(element.getAttribute("origin"));
        VisualParameters parameters = mineral.getParameters();
        Element parametersElement = (Element) element.getElementsByTagName("visual-parameters").item(0);
        parameters.setColor(getElementTextContent(parametersElement, "color"));
        parameters.setTransparency(Integer.parseInt(getElementTextContent(parametersElement, "transparency")));
        parameters.setCut(Integer.parseInt(getElementTextContent(parametersElement, "cut")));
        mineral.setValue(Double.parseDouble(getElementTextContent(element, "value")));
        String date = getElementTextContent(element, "date-of-processing");
        mineral.setDate(LocalDate.parse(date));
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

