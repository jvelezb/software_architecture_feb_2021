package services;

public class ProxyCount implements ServiceInterface {

	/**
	 * Count the number of times the services is called 
	 */
	private int NUMBER_ACCESS = 0;
	/**
	 * The original service will be called 
	 */
	ServiceInterface service;

	public ProxyCount(ServiceInterface other) {
		this.service = other;
	}

	@Override
	public boolean checkNumber(int number) throws Exception {
		count();
		System.out.println("Number of accesses: " + NUMBER_ACCESS);
		return service.checkNumber(number);
	}

	/** static
	 * Increase the number of the original service is called.
	 * <br> Preprocess before call service 
	 */
	private void count() {
		NUMBER_ACCESS++;
	}

}
