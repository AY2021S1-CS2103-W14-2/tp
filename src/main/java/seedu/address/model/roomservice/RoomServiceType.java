package seedu.address.model.roomservice;

public enum RoomServiceType {
    WIFI("WIFI", "Wifi service", 40),
    DINING("DINING", "Dining in service", 50),
    MASSAGE("MASSAGE", "Massaging service", 70);

    private final String name;
    private final String verboseName;
    private final Integer price;

    RoomServiceType(String name, String verboseName, Integer price) {
        this.name = name;
        this.verboseName = verboseName;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getVerboseName() {
        return this.verboseName;
    }

    public Integer getPrice() {
        return this.price;
    }
}
