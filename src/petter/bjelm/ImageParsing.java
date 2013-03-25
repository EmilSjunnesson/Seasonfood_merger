package petter.bjelm;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ImageParsing {

	private ArrayList<String> imgNamesArray = new ArrayList<String>();
	private ArrayList<String> imgURLArray = new ArrayList<String>();
	private Document doc;
	private Document imgDoc;

	public ImageParsing() {

	}

	//Takes the xml document returns an array of thumbnail URL:s
	public ArrayList<String> getURLArray(Document docIn) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		System.out.println("Loading image URL:s......");

		// clears the array every time the code runs (button is pressed)
		imgNamesArray.clear();
		imgURLArray.clear();

		doc = docIn;

		Element root = doc.getDocumentElement();
		NodeList nodes = root.getElementsByTagName("value");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node data = nodes.item(i);
			
			//Gets the file name of the image
			if (data instanceof Element) {
				Element name = (Element) data;
				imgNamesArray.add(name.getAttribute("fulltext"));

			}
		}

		for (int i = 0; i < imgNamesArray.size(); i++) {
			imgURLArray.add(parseImg(imgNamesArray.get(i)));
		}
		return imgURLArray;
	}
	
	//Gets thumbnail image URL from the insertet file names
	public String parseImg(String imgText) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {
		String s = null;
		URL url = new URL(
				"http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=query&titles="
						+ imgText.replace(" ", "%20")
						+ "&prop=imageinfo&iiprop=url&iiurlwidth=180");
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		imgDoc = builder.parse(conn.getInputStream());

		imgDoc.getDocumentElement().normalize();

		Element imgRoot = imgDoc.getDocumentElement();
		NodeList imgNodes = imgRoot.getElementsByTagName("ii");
		for (int i = 0; i < imgNodes.getLength(); i++) {
			Node imgData = imgNodes.item(i);

			if (imgData instanceof Element) {
				Element name = (Element) imgData;
				s = name.getAttribute("thumburl");

			}
		}
		return s;
	}
}