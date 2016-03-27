import java.util.Random;
import java.io.IOException;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Anno {
	private static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
	private static final String site = "http://www.google.com/search?q=fun+fact&gws_rd=cr";
	private static final Random RND = new Random();

	static int MIN_TIME = 1000;
	static int MAX_TIME = 3000;

	static Document document;

	public static void main(String[] args) {

		try {
			MIN_TIME = Integer.parseInt(args[0]);
			MAX_TIME = Integer.parseInt(args[1]);
		} catch (Exception e) {
		}

		int waitTime;
		for (;;) {
			try {
				try {
					document = Jsoup.connect(site).userAgent(USER_AGENT).timeout(5000).get();
					System.out.println(document);
					Elements e = document.body().select("div#_OKe");
					System.out.println(e.size());

				} catch (IOException e1) {
					e1.printStackTrace();
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

	private static int getSleepTime() {
		return MIN_TIME + RND.nextInt(MAX_TIME - MIN_TIME + 1);
	}

	private static void rawMsg(String msg, String title, int type) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null, msg, title, type);
	}

	private static int getScreenWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	private static int getsScreenHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
