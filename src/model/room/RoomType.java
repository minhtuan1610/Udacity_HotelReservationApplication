package model.room;

public enum RoomType {
	SINGLE("1"),
	DOUBLE("2");

	public final String type;

	private RoomType(String type) {
		this.type = type;
	}
}
