package contact;

class Contact {
    private final String name;
    private final String phoneNumber7;

    public Contact(String name, String phoneNumber7) {
        this.name = name;
        this.phoneNumber7 = phoneNumber7;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber7() {
        return phoneNumber7;
    }

    @Override
    public String toString() {
        return name + " | " + phoneNumber7;
    }
}