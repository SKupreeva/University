import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Lab_4 {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        docFactory.setIgnoringComments(true);
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.parse("order.xml");
        Factory fact = new Factory();
        int flag = 0, k = 1;
        Menu menu = new Menu();
        Iterator iterator = menu.getIterator();
        System.out.println("\n\n\tMenu of our restaraunt:");
        while (iterator.hasNext()) {
            System.out.println(k + ". " + iterator.next() + ".");
            k++;
        }
        System.out.println("\n\n");
        order orderDecorated = new orderDecorated(new newOrder());
        orderDecorated.print();

        Node ord = document.getDocumentElement();
        NodeList clientInfo = ord.getChildNodes();
        System.out.println("\n\tOrder Info");
        for(int i = 0; i < clientInfo.getLength(); i++){
            Node item = clientInfo.item(i);
            if(item.getNodeType() != Node.TEXT_NODE){
                NodeList itemsProps = item.getChildNodes();
                for(int j = 0; j < itemsProps.getLength(); j++){
                    Node itemProp = itemsProps.item(j);
                    if (itemProp.getNodeType() != Node.TEXT_NODE) {
                        System.out.println(itemProp.getNodeName() + ":"
                                + itemProp.getChildNodes().item(0).getTextContent());
                        flag++;
                    }
                }
                System.out.println("---------------");
            }
        }
        if(flag == 0) {
            infoType it = fact.getType("Fail");
            it.showType();
        } else {
            infoType it = fact.getType("Success");
            it.showType();
        }
    }
}
