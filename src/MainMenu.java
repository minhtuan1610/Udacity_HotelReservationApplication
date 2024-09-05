import api.HotelResource;

import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getHotelResourceInstance();
    private static int choice = -1;

    public static void mainMenu() {
        while (choice != 0) {
            displayMainMenu();
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Enter email formatted as something@domain.com");
                    String email = input.nextLine();
                    System.out.println("What is your first name?");
                    String firstName = input.nextLine();
                    System.out.println("What is your last name?");
                    String lastName = input.nextLine();
                    hotelResource.createCustomer(email, firstName, lastName);
                    break;
                case 4:
                    AdminMenu.adminMenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
}
