package petter.bjelm;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetCategory {

	public String chosenSort;
	public String chosenDate;
	public ArrayList<String> sortArray = new ArrayList<String>();
	public URL url;
	URLConnection conn;
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document doc;
	TransformerFactory tfactory;
	Transformer xform;
	Node nNode;
	NodeList nList;
	Element eElement;

	public GetCategory() {

	}

	public void setCategory(String name) {

		chosenSort = name;

	}

	public void setDate(String date) {

		chosenDate = date;

	}

	public ArrayList<String> getArray() throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		sortArray.clear();

		url = new URL(
				"http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:"
						+ chosenSort + "]][[I+s�song+Z3::1912-" + chosenDate
						+ "-15]]|?bild");
		conn = url.openConnection();

		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse(conn.getInputStream());

		tfactory = TransformerFactory.newInstance();
		xform = tfactory.newTransformer();

		// that�s the default xform; use a stylesheet to get a real one
		// xform.transform(new DOMSource(doc), new StreamResult(System.out));

		doc.getDocumentElement().normalize();

		//System.out.println(doc);

		nList = doc.getElementsByTagName("results");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			nNode = nList.item(temp);

			// System.out.println("\nCurrent Element :" + nNode.getNodeName());

			for (int temp2 = 0; temp2 < nNode.getChildNodes().getLength(); temp2++) {

				sortArray.add(temp2, ""
						+ nNode.getChildNodes().item(temp2).getNodeName());

			}
		}

		return sortArray;

	}

}