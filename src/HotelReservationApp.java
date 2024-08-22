import model.customer.Customer;
import model.room.FreeRoom;
import model.room.RoomType;

public class HotelReservationApp {
	public static void main(String[] args) {
		Customer customer = new Customer("test", "test", "test@gmail.com");
		FreeRoom freeRoom = new FreeRoom("test", RoomType.SINGLE);
		System.out.println(customer + ", " + freeRoom);
	}
}
