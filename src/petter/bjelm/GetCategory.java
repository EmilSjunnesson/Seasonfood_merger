package petter.bjelm;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetCategory {

	public ArrayList<String> sortArray = new ArrayList<String>();
	Document doc;
	Node nNode;
	NodeList nList;
	Element eElement;

	public GetCategory() {

	}

	public ArrayList<String> getArray(Document docIn) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		sortArray.clear();

		doc = docIn;
		
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