package contact;

public class Contacts {

    private String name;

    private String phoneNumber7;

    private String phoneNumber10;


    public void contact(String name, String number) {
        this.name = name;
        this.phoneNumber7 = number;
        this.phoneNumber10 = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber7() {
        return phoneNumber7;
    }

    public void setPhoneNumber7(String phoneNumber7) {
        this.phoneNumber7 = phoneNumber7;
    }

    public String getPhoneNumber10() {
        return phoneNumber10;
    }

    public void setPhoneNumber10(String phoneNumber10) {
        this.phoneNumber10 = phoneNumber10;
    }
}
