package by.task.komar.handler;

import by.task.komar.fund.Mineral;
import by.task.komar.fund.Name;
import by.task.komar.fund.Precious;
import by.task.komar.fund.Semiprecious;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FundHandler extends DefaultHandler {
    private Set<Mineral> minerals;
    private Mineral current;
    private FundXmlTag currentXmlTag;
    private EnumSet<FundXmlTag> withText;

    public FundHandler() {
        minerals = new HashSet<>();
        withText = EnumSet.range(FundXmlTag.COLOR, FundXmlTag.DATE_OF_PROCESSING);
    }

    public Set<Mineral> getMinerals() {
        return minerals;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (FundXmlTag.PRECIOUS.getTitle().equals(qName)) {
            current = new Precious();
            Name name = Name.valueOf(attrs.getValue(0).toUpperCase());
            ((Precious) current).setName(name);
            if (attrs.getLength() == 2) {
                current.setOrigin(attrs.getValue(1));
            }
        } else if (FundXmlTag.SEMIPRECIOUS.getTitle().equals(qName)) {
            current = new Semiprecious();
            ((Semiprecious) current).setName(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setOrigin(attrs.getValue(1));
            }
        } else {
            FundXmlTag temp = FundXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (FundXmlTag.PRECIOUS.getTitle().equals(qName)
                || FundXmlTag.SEMIPRECIOUS.getTitle().equals(qName)) {
            minerals.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case COLOR:
                    current.getParameters().setColor(data);
                    break;
                case TRANSPARENCY:
                    current.getParameters().setTransparency(Integer.parseInt(data));
                    break;
                case CUT:
                    current.getParameters().setCut(Integer.parseInt(data));
                    break;
                case VALUE:
                    current.setValue(Double.parseDouble(data));
                    break;
                case DATE_OF_PROCESSING:
                    current.setDate(LocalDate.parse(data));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}

