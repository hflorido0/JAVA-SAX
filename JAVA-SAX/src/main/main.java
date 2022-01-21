package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import sax.SaxNewDocument;
import sax.SaxReader;

public class main {

	public static void main(String[] args) {
		//reading an existing xml document
		SaxReader saxReader = new SaxReader();
		saxReader.init("files/productos.xml");
		
		//Crating a new xml document
		SaxNewDocument saxNewDocument = new SaxNewDocument();
		saxNewDocument.generateDocument("files/productosResult.xml");
	}

}
