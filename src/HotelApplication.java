import java.text.ParseException;
import java.util.InputMismatchException;

public class HotelApplication {
	public static void main(String[] args) {
		boolean valid = true;
		while (valid) {
			try {
				MainMenu.mainMenu();
				valid = false;
			} catch (InputMismatchException e) {
				System.out.println("Wrong number");
			}
		}
	}
}
