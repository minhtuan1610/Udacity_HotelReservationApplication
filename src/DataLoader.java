import model.customer.Customer;
import model.reservation.Reservation;
import model.room.Room;
import model.room.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataLoader {
	public static void createTestData() throws ParseException {
		Room room1 = new Room("1A", 0.0, RoomType.SINGLE);
		Room room2 = new Room("2A", 1.0, RoomType.SINGLE);
		Room room3 = new Room("3B", 2.5, RoomType.DOUBLE);
		Room room4 = new Room("4C", 2.0, RoomType.DOUBLE);
		Room room5 = new Room("5A", 5.5, RoomType.DOUBLE);
		Room roomBook6 = new Room("6AB", 3.5, RoomType.SINGLE);
		Room roomBook7 = new Room("7AB", 4.5, RoomType.DOUBLE);
		Customer cus1 = new Customer("cus1", "tomer1", "cus1@domain.com");
		Customer cus2 = new Customer("cus2", "tomer2", "cus2@domain.com");
		Customer cus3 = new Customer("cus3", "tomer3", "cus3@domain.com");
		Customer cus4 = new Customer("cus4", "tomer4", "cus4@domain.com");
		Customer cus5 = new Customer("cus5", "tomer5", "cus5@domain.com");
		Customer cusBook6 = new Customer("cusBook6", "tomer6", "cusBook6@domain.com");
		Customer cusBook7 = new Customer("cusBook7", "tomer7", "cusBook7@domain.com");
		Reservation res6 = new Reservation(cusBook6, roomBook6,
				getFormattedDate(2024, 10, 1),
				getFormattedDate(2024, 10, 5));
		Reservation res7 = new Reservation(cusBook7, roomBook7,
				getFormattedDate(2024, 11, 1),
				getFormattedDate(2024, 11, 5));

	}

	private static Date getFormattedDate(int year, int month, int day) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dayStr = year + "-" + month + "-" + day;
		return simpleDateFormat.parse(dayStr);
	}

}
