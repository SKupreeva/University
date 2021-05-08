package com.company;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Client {

    static String fileXml;
    static SOAPMessage reqMess, respMess;

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.start();
        client.createSOAPRequest();
        SOAPConnectionFactory soapConnectionFactory =
                SOAPConnectionFactory.newInstance();
        SOAPConnection connection =
                soapConnectionFactory.createConnection();
        URL endpoint = new
                URL("http://localhost:8080/axis/services/Server");
        respMess = connection.call(reqMess, endpoint);
        System.out.println(respMess);
        displayMessage(respMess);
    }

    public void createSOAPRequest() throws SOAPException {
        MessageFactory factory = MessageFactory.newInstance();
        reqMess = factory.createMessage();
        MimeHeaders mimeHeader = reqMess.getMimeHeaders();

        mimeHeader.setHeader("SOAPAction", "");

        SOAPPart soapPart = reqMess.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody body = envelope.getBody();
        header.detachNode();
        Name bodyName = envelope.createName("GetFile");
        SOAPBodyElement bodyElement =
                body.addBodyElement(bodyName);
        Name name = envelope.createName("file", "", "");
        SOAPElement fileName = bodyElement.addChildElement(name);
        fileName.addTextNode(fileXml);
    }

    public static void displayMessage(SOAPMessage msg) throws SOAPException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        msg.writeTo(out);
        String strMsg = new String(out.toByteArray());
        System.out.println(strMsg);
    }

    public void start() {
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        try {
            System.out.println("Enter the path to the XML file:");
            fileXml = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
