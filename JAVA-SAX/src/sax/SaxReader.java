package sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Product;

public class SaxReader extends DefaultHandler {
	ArrayList<Product> products;
	Product product;
	String value;
	
	public ArrayList<Product> init(String fil) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File file = new File (fil);
			parser.parse(file, this);
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}	
		
		return this.products;
	}
	
	@Override
	public void startDocument() throws SAXException {
		this.products = new ArrayList<>();
	}

	@Override
	public void endDocument() throws SAXException {
		printDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
			case "product":
				this.product = new Product(attributes.getValue("name") != null? attributes.getValue("name") : "empty");
				break;
			case "price":
				this.product.setBadge(attributes.getValue("badge"));
				break;
			case "stock":
				this.product.setColor(attributes.getValue("color"));
				this.product.setStorage(Integer.parseInt(attributes.getValue("storage")));
				break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//we add the product into the arrayList
		if (qName.equals("product")) this.products.add(product);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = new String(ch,start,length);
		
		//if value contains a "." is because it's a float do it goes to price attribute
		//if not it goes to stock attribute
		if (value.matches("[0-9]*\\.[0-9]*")) this.product.setPrice(Float.valueOf(value));
		else if(value.matches("[0-9]*")) this.product.setStock(Integer.valueOf(value));
	}

	private void printDocument() {
		for (Product p : products) {
			System.out.println(p.toString());
		}
	}

}
