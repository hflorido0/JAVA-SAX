package dom;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.xml.transform.TransformerFactory;

public class SaxNewDocument {
	private StringBuffer buffer;
	
	public SaxNewDocument() {
		this.buffer = new StringBuffer();
	}
	

	public void generateDocument() {
		//version
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		//PARENT NODE
		//root node
		buffer.append("<products>\n");
		
		//CHILD NODES
		//child node into root node "products"
		buffer.append("\t<product>\n");
		
		//FINAL NODES
		//child into product with attribute and content
		buffer.append("\t\t<price badge=\"Euro\">29.99</price>\n");
		
		//child into product with 2 attributes and content
		buffer.append("\t\t<stock color=\"blue\" storage=\"22\">22</stock>\n");
		
		//end tag
		buffer.append("\t</product>\n");
		
		//another child node for root node "products"
		//this one with attribute
		buffer.append("\t<product name=\"product 2\">\n");
		
		//child into product with attribute and content
		buffer.append("\t\t<price badge=\"Dollar\">14.95</price>\n");
		
		//child into product with 2 attributes and content
		buffer.append("\t\t<stock color=\"red\" storage=\"40\">21</stock>\n");
		
		//end tags
		buffer.append("\t</product>\n");
		buffer.append("</products>");
		
		generateXml();
	}
	
	private void generateXml() {
		TransformerFactory factory = TransformerFactory.newInstance();
		try {			
			File file = new File("files/productosResult.xml");
			FileWriter fw = new FileWriter(file);
			
			PrintWriter pw = new PrintWriter(fw);
			pw.write(buffer.toString());	
			pw.close();
		} catch (IOException e) {
			System.out.println("Error when creating writter file");
		}
	}
}
