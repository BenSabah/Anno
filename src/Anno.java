import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Anno {
	static int MIN_TIME = 1000;
	static int MAX_TIME = 3000;
	static Random rnd = new Random();
	static String site = "https://www.google.com/search?q=fun+fact";
	static Document document;
	static Rectangle frame = new Rectangle(0, 0, getScreenWidth(), getsScreenHeight());
	private static DateFormat timeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

	public static void main(String[] args) {

		try {
			MIN_TIME = Integer.parseInt(args[0]);
			MAX_TIME = Integer.parseInt(args[1]);
		} catch (Exception e) {
		}

		Robot robot1 = null;
		try {
			robot1 = new Robot();
		} catch (AWTException e2) {
		}

		int waitTime;
		for (;;) {
			try {
				try {
					if (robot1 != null) {
						BufferedImage buff = robot1.createScreenCapture(frame);
						String time = timeFormat.format(System.currentTimeMillis());
						System.out.println(time);
						File path = new File("pic_" + System.currentTimeMillis() + ".png");
						ImageIO.write(buff, "png", path);
						System.out.println(path);
					}

					// document = Jsoup.connect(site).followRedirects(true).timeout(1000).get();
					// Elements value2 = document.body().select("#_dNh");
					// System.out.println(document);
					// Element s = value2.get(1);
					// String a = s.text();
					// String value = document.body().select("#element1").get(0).text();
					// System.out.println(value2);
					// System.out.println(s);
					// System.out.println(a);

				} catch (IOException e1) {
					System.out.println("timeout");
					continue;
				}

				waitTime = getSleepTime();
				Thread.sleep(waitTime);
				// System.out.println("waited " + waitTime + "ms");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// rawMsg("", "hello !", 2);
		}
	}

	public static int getSleepTime() {
		return MIN_TIME + rnd.nextInt(MAX_TIME - MIN_TIME + 1);
	}

	public static void rawMsg(String msg, String title, int type) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	static int getScreenWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	static int getsScreenHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
