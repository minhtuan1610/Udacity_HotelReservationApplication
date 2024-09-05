import java.util.Scanner;

public class AdminMenu {
    private static int choiceAdmin = -1;

    public static void adminMenu() {
        while (choiceAdmin != 0) {
            displayAdminMenu();
            Scanner input = new Scanner(System.in);
            choiceAdmin = input.nextInt();
            switch (choiceAdmin) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    MainMenu.mainMenu();
                    break;
            }
        }
    }

    private static void displayAdminMenu() {
        System.out.println("--------------------------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. Add a Room");
        System.out.println("4. Back to Main Menu");
        System.out.println("--------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
}
