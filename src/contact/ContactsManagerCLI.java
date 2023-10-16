package contact;

import java.io.File;
import java.util.ArrayList;

public class ContactsManagerCLI {
    private static List<Contact> contacts;
    private static File file;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        file = new File("contacts.txt");

        loadContacts();

        int choice;
        do {
            choice = showMainMenu();
            switch (choice) {
                case 1:
                    viewContacts();
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
        } while (choice != 5);

        saveContacts();
    }
}