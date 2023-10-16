package contact;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static contact.Contacts.addContact;

public class ContactsManagerCLI {
//    private static List<Contact> contacts;
//    private static File file;

    public static String showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("1. View contacts.%n2. Add a new contact.%n3. Search a contact by name.%n4. Delete an existing contact.%n5. Exit.%nEnter an option (1, 2, 3, 4 or 5): %n");
        String showMainMenu = scanner.next();
        return showMainMenu;
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        contacts = new ArrayList<>();
//        file = new File("contacts.txt");

        Path viewContacts = Paths.get("./src/data/contacts");

        String choice;
        do {
            choice = showMainMenu();
            switch (choice) {
                case 1:
                    System.out.println(viewContacts);
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (choice != "5");

        saveContacts();
    }
}