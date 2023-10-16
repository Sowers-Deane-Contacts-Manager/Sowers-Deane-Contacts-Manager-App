package contact;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.sql.SQLOutput;

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

//    public String showMainMenu() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.printf("1. View contacts.%n2. Add a new contact.%n3. Search a contact by name.%n4. Delete an existing contact.%n5. Exit.%nEnter an option (1, 2, 3, 4 or 5): %n");
//        String showMainMenu = scanner.next();
//    };

    public static String addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please provide \"First Last\" name and 7 digit phone number \"xxx-xxxx\": ");
        String addContact = scanner.next();
        Files.write (
                Paths.get("data", "contacts.txt"),
                Arrays.asList(addContact),
                StandardOpenOption.APPEND
        );
    }
}
