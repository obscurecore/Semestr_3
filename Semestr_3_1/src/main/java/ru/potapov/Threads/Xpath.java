package ru.potapov.Threads;

import org.w3c.dom.NodeList;
import javax.swing.text.Document;
import javax.xml.xpath.*;
import java.io.File;
import java.util.List;

public class Xpath {
    public List xpath(File file,String EXPRESSION) throws Exception {
        List<String> lt = null;
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr;
        Document document = (Document) file;
        try {
            expr = xpath.compile(EXPRESSION);
        } catch (XPathExpressionException ex) {
            throw new Exception("Can't parse xPath expression " + EXPRESSION);
        }

        NodeList nodeList;
        try {
          lt   = (List<String>) expr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            throw new Exception("Can't evaluate exression");
        }



        return lt;
    }
    public static List folder (String way, String exp) throws Exception {
        Xpath xpath = new Xpath();
        List list=  null;
        File folder = new File(way);
        File[] listOfFiles = folder.listFiles();
        NodeList nodeList;
        for (File file : listOfFiles) {
            if (file.isFile()) {

                }
                list.addAll( xpath.xpath(file,exp));

            }
        return list;
        }
    }

