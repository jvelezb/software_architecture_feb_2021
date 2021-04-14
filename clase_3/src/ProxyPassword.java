package services;


public class ProxyPassword implements ServiceInterface {

	/**
	 * Original service will be executed
	 */
	ServiceInterface services;
	/**
	 * input password
	 */
	String password;

	public ProxyPassword(ServiceInterface other, String pass) {
		this.services = other;
		this.password = pass;
	}

	@Override
	public boolean checkNumber(int number) throws Exception {
		if (this.password.equals("pass")) {
			System.out.println("Correct password!");
			return services.checkNumber(number);
		} else {
			System.out.println("Wrong password!");
			throw new Exception("Cannot executed! Wrong password!");
		}
	}
}
