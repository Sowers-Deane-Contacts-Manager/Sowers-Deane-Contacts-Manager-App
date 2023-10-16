package contact;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static contact.Contacts.addContact;

public class ContactsManagerCLI {
//    private static List<Contact> contacts;
//    private static File file;

    public static int showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("1. View contacts.%n2. Add a new contact.%n3. Search a contact by name.%n4. Delete an existing contact.%n5. Exit.%nEnter an option (1, 2, 3, 4 or 5): %n");
        int showMainMenu = scanner.nextInt();
        return showMainMenu;
    };

    public static String viewContacts() throws IOException {
        Path contactsPath = Paths.get("src/data", "contacts.txt");
        List<String> contactsList = Files.readAllLines(contactsPath);
        for (int i =0; i < contactsList.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactsList.get(i));
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

//        contacts = new ArrayList<>();
//        file = new File("contacts.txt");

        Path contactsFile = Paths.get("./data/contacts", "contacts.txt");



        int choice;
        do {
            choice = showMainMenu();
            switch (choice) {
                case 1:
                    System.out.println(viewContacts());
                    break;
                case 2:
                    try {
                        addContact();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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
        } while (choice != 5);

        saveContacts();
    }

    private static void searchContact() {

    }

    private static void deleteContact() {

    }

    private static void saveContacts() {

    }
}