package petter.bjelm;

import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Parse {

	private String monthText = "00";
	private String type = "Grönsaker";
	private String zone = "Z3";
	private Document doc;

	public Parse() {

	}

	public Document ParseDoc(String typeIn) throws Exception {
		
		getCurrDate();
		type = typeIn;

		URL url = new URL(
				"http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:"
						+ type + "]][[I+säsong+"+zone+"::1912-" + monthText + "-"
						+ "15" + "]]|?bild");
		URLConnection conn = url.openConnection();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(conn.getInputStream());

		doc.getDocumentElement().normalize();
		System.out.println(url);
		return doc;

	}

	// * Ändrar URL till dagens datum */
	public String getCurrDate() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthText = "0" + Integer.toString(month);
		} else {
			monthText = Integer.toString(month);
		}
		return monthText;
	}

	public void setDate(String date) {
		monthText = date;
	}
	
	public void setZone(String area) {
		zone = area;
	}
}
