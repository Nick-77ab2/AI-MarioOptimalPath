package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class TreeCreator {
    public ArrayList<String> tempHolder = new ArrayList<>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public ArrayList<String> convertXML() {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("mostPoints.xml");
            NodeList sequenceList = doc.getElementsByTagName("sequence");
            for (int i = 0; i < sequenceList.getLength(); i++) {
                Node s = sequenceList.item(i);
                if (s.getNodeType() == Node.ELEMENT_NODE) {
                    Element sequence = (Element) s;
                    NodeList nameList = sequence.getChildNodes();
                    for (int j = 0; j < nameList.getLength(); j++) {
                        Node n = nameList.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element name1 = (Element) n;
                            tempHolder.add(name1.getTextContent());
                        }
                    }
                }
            }
        } catch (
                ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return tempHolder;
    }
}

//Create the task and the default function run
interface Task {
    boolean run(BehaviorTreeAgent c);
}
//Create the Composite as a wrapper for the Sequence
interface Composite extends ch.idsia.agents.btcompiler.Task {
}
