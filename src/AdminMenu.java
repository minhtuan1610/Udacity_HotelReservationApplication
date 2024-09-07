import api.AdminResource;
import api.DataInput;
import model.room.IRoom;
import model.room.Room;
import model.room.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
	private static final AdminResource ADMIN_RESOURCE = AdminResource.getAdminResourceInstance();
	private static final DataInput INPUT = DataInput.getDataInput();
	private static final List<IRoom> rooms = new ArrayList<>();
	private static int choiceAdmin = -1;

	public static void adminMenu() {
		while (choiceAdmin != 0) {
			displayAdminMenu();
			choiceAdmin = INPUT.getIntInput();
			switch (choiceAdmin) {
				case 1 -> ADMIN_RESOURCE.getAllCustomers(); // See all customers
				case 2 -> ADMIN_RESOURCE.getAllRooms(); // See all rooms
				case 3 -> ADMIN_RESOURCE.displayAllReservations(); //See all Reservations
				case 4 -> {
					do {
						System.out.println("Enter room number");
						String rNo = INPUT.getStringInput();
						System.out.println("Enter price per night");
						Double rPri = INPUT.getDoubleInput();
						System.out.println("Enter room type: 1 for single bed, 2 for double bed");
						String rType = INPUT.getStringInput();
						while (!rType.equalsIgnoreCase("1") && !rType.equalsIgnoreCase("2")) {
							System.out.println("Type: 1 for single bed, 2 for double bed");
							rType = INPUT.getStringInput();
						}
						if (rType.equalsIgnoreCase("1")) {
							rooms.add(new Room(rNo, rPri, RoomType.SINGLE));
						} else {
							rooms.add(new Room(rNo, rPri, RoomType.DOUBLE));
						}
						ADMIN_RESOURCE.addRoom(rooms);
						System.out.println("Would you like to add another room? (Please type y/n)");
						String text = INPUT.getStringInput();
						while (!text.equalsIgnoreCase("y") && !text.equalsIgnoreCase("n")) {
							System.out.println("Would you like to add another room? (Please type y/n)");
							text = INPUT.getStringInput();
						}
						if (text.equalsIgnoreCase("y")) {
							choiceAdmin = 4;
						} else {
							choiceAdmin = -1;
						}
					} while (choiceAdmin == 4);

				}
				case 5 -> MainMenu.mainMenu();
			}
		}
	}

	private static void displayAdminMenu() {
		System.out.println("--------------------------------------------------");
		System.out.println("1. See all Customers");
		System.out.println("2. See all Rooms");
		System.out.println("3. See all Reservations");
		System.out.println("4. Add a Room");
		System.out.println("5. Back to Main Menu");
		System.out.println("--------------------------------------------------");
		System.out.println("Please select a number for the menu option");
	}
}
