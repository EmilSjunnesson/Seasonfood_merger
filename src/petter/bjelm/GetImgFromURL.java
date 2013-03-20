package petter.bjelm;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class GetImgFromURL {

	public GetImgFromURL() {

	};

	public BufferedImage getURLImage(String imgURL) {
		BufferedImage image = null;
		try {
			URL url = new URL(imgURL);
			image = ImageIO.read(url);
		} catch (Exception exp) {

		}
		return image;
	};
}
