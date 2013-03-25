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

	private ArrayList<String> sortArray = new ArrayList<String>();
	private Document doc;
	private Node nNode;
	private NodeList nList;

	public GetCategory() {

	}
	
	//Takes the xml document returns an array of food names in season
	public ArrayList<String> getArray(Document docIn) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		// clears the array every time the code runs (button is pressed)
		sortArray.clear();
		
		doc = docIn;
		
		nList = doc.getElementsByTagName("results");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			nNode = nList.item(temp);

			for (int temp2 = 0; temp2 < nNode.getChildNodes().getLength(); temp2++) {
				sortArray.add(temp2, ""
						+ nNode.getChildNodes().item(temp2).getNodeName());
			}
		}
		return sortArray;
	}

}