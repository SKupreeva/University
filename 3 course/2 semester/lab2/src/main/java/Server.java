import org.apache.axis.AxisFault;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Server {

    public void getInfo(SOAPEnvelope req, SOAPEnvelope resp) throws Exception, TransformerException, IOException {
        String fileName = getFileName(req);
        createSOAPResponse(fileName, resp);
    }

    public String getFileName(SOAPEnvelope req) throws AxisFault {
        SOAPBodyElement reqBody = (SOAPBodyElement)
                req.getBodyElements().get(0);
        String str = "";
        NodeList nodes = reqBody.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);
            if (currentNode instanceof Element) {
                Element element = (Element) currentNode;
                Text textNode = (Text) element.getFirstChild();
                str = textNode.getData().trim();
            }
        }
        return str;
    }

    public void createSOAPResponse(String fileName, SOAPEnvelope resp) throws SOAPException,
            ParserConfigurationException,
            IOException, SAXException {
        DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();
        Document document =
                builder.parse("D:/" + fileName);
        SOAPHeader header = resp.getHeader();
        SOAPBody body = resp.getBody();
        header.detachNode();
        body.addDocument(document);
    }
}

