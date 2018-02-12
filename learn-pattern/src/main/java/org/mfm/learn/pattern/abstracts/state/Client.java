package org.mfm.learn.pattern.abstracts.state;

/**
 *
 * @author admin
 *
 */
public class Client {
	public static void main(String[] args) {
		Context c = new Context();
		c.request();
		c.request();
		c.request();
	}
}
