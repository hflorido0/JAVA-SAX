package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import dom.SaxNewDocument;
import dom.SaxReader;

public class main {

	public static void main(String[] args) {

		//reading an existing xml document
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File file = new File ("files/productos.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}
		
		//Crating a new xml document
		SaxNewDocument saxNewDocument = new SaxNewDocument();
		saxNewDocument.generateDocument();
	}

}
