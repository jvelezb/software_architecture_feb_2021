package services;


public class OriginalService implements ServiceInterface {

	@Override
	public boolean checkNumber(int number) throws Exception {
		boolean even = (number % 2 == 0);
		if (even)
			System.out.println(number + " is a even number");
		else
			System.out.println(number + " isn't a even number");
		return even;
	}

}
