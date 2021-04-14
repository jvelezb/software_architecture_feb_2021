package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.OriginalService;
import services.ProxyCount;
import services.ProxyPassword;
import services.ServiceInterface;
import clients.Client;


public class ClientService1Test {

	/**
	 * "pass" : correct password
	 */
	String passOK = "pass";

	/**
	 * "password": wrong password
	 */
	String passKO = "password";

	private Client client;
	/**
	 * Services will be executed when the client call
	 * <br> We can change the services to executed other function 
	 */
	private ServiceInterface services;
	/**
	 * The original service of proxy
	 */
	private ServiceInterface original;
	/**
	 * The proxy provide for client
	 */
	private ServiceInterface proxy;

	@Before
	public void setUp() {
		services = new OriginalService();
	}

	/**
	 * EXPECTED RESULT: <br>
	 * Original: services.OriginalService@672bb3 <br>
	 * Proxy: services.OriginalService@672bb3 <br>
	 * 4 is a even number <br>
	 * 5 isn't a even number <br>
	 * @throws Exception
	 */
	@Test
	public void testOriginalServices() throws Exception {
		original = services;
		proxy = original;
		executeTest();
	}

	/**
	 * EXPECTED RESULT: <br>
	 * Original: services.OriginalService@ea01d7 <br>
	Proxy: services.ProxyPassword@11ed915 <br>
	Correct password! <br>
	4 is a even number <br>
	Correct password! <br>
	5 isn't a even number <br>
	 * @throws Exception
	 */
	@Test
	public void testProxyPasswordOK() throws Exception {
		original = services;
		proxy = new ProxyPassword(original, passOK);
		executeTest();
	}

	/**
	 * EXPECTED RESULT: <br>
	 * Original: services.OriginalService@19747c9 <br>
	Proxy: services.ProxyPassword@ea01d7 <br>
	Wrong password! <br>
	 * @throws Exception java.lang.Exception: Cannot executed! Wrong password!
	 */
	@Test
	public void testProxyPasswordKO() throws Exception {
		original = services;
		proxy = new ProxyPassword(original, passKO);
		executeTest();
	}

	/**
	 * EXPECTED RESULT: <br>
	 * Original: services.OriginalService@d03e03 <br>
	Proxy: services.ProxyCount@19747c9<br>
	Number of accesses: 1<br>
	4 is a even number<br>
	Number of accesses: 2<br>
	5 isn't a even number<br>
	 * @throws Exception
	 */
	@Test
	public void testProxyCount() throws Exception {
		original = services;
		proxy = new ProxyCount(original);
		executeTest();
	}

	/**
	 * EXPECTED RESULT:<br>
	 * Original: services.ProxyPassword@22023a<br>
	Proxy: services.ProxyCount@c04fc1<br>
	Number of accesses: 1<br>
	Correct password!<br>
	4 is a even number<br>
	Number of accesses: 2<br>
	Correct password!<br>
	5 isn't a even number<br>
	 * @throws Exception
	 */
	@Test
	public void testProxyPasswordOKCount() throws Exception {
		original = new ProxyPassword(services, passOK);
		proxy = new ProxyCount(original);
		executeTest();
	}

	/**
	 * EXPECTED RESULT:<br>
	 * Original: services.ProxyPassword@c04fc1<br>
	Proxy: services.ProxyCount@1b42c30<br>
	Number of accesses: 1<br>
	Wrong password!<br>
	 * @throws Exception java.lang.Exception: Cannot executed! Wrong password!
	 */
	@Test
	public void testProxyPasswordKOCount() throws Exception {
		original = new ProxyPassword(services, passKO);
		proxy = new ProxyCount(original);
		executeTest();
	}

	/**
	 * EXPECTED RESULT:<br>
	 * Original: services.ProxyCount@c04fc1<br>
	Proxy: services.ProxyPassword@1b42c30<br>
	Correct password!<br>
	Number of accesses: 1<br>
	4 is a even number<br>
	Correct password!<br>
	Number of accesses: 2<br>
	5 isn't a even number<br>
	 * @throws Exception
	 */
	@Test
	public void testProxyCountPasswordOK() throws Exception {
		original = new ProxyCount(services);
		proxy = new ProxyPassword(original, passOK);
		executeTest();
	}

	/**
	 * EXPECTED RESULT: <br>
	 * Original: services.ProxyCount@22023a<br>
	Proxy: services.ProxyPassword@c04fc1<br>
	Wrong password!<br>
	 * @throws Exception java.lang.Exception: Cannot executed! Wrong password!
	 */
	@Test
	public void testProxyCountPasswordKO() throws Exception {
		original = new ProxyCount(services);
		proxy = new ProxyPassword(original, passKO);
		executeTest();
	}

	/**
	 * Reset parameter
	 */
	@After
	public void tearDown() {
		original = null;
		proxy = null;
		client = null;
	}

	/**
	 * Executed test with the numbers to check are 4 and 5.
	 * <br> The services will return true with 4
	 * <br> The services will return false with 5
	 * <br> The exception will be returned when the password is wrong. 
	 * @throws Exception
	 */
	private void executeTest() throws Exception {
		System.out.println("\nOriginal: " + original.toString());
		System.out.println("Proxy: " + proxy.toString());
		// Setup client
		client = new Client(proxy);

		// Execute Test
		assertTrue(client.callService(4));
		assertFalse(client.callService(5));
	}

}
