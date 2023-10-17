package contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ContactsManagerCLI {
    private static List<Contact> contacts;
    private static final String FILE_NAME = "src/data/contacts.txt";

    public static void main(String[] args) {
        contacts = loadContacts();
        boolean exit = false;

        while (!exit) {
            int choice = displayMainMenu();

            switch (choice) {
                case 1:
                    viewContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContacts();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        saveContacts();
        System.out.println("Exiting the application. Contacts saved successfully.");
    }

    private static List<Contact> loadContacts() {
        List<Contact> contactList = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String phoneNumber = parts[0].trim();
                contactList.add(new Contact(name, phoneNumber));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No contacts file found. Starting with an empty contact list.");
        }

        return contactList;
    }

    private static int displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.print("Enter an option (1, 2, 3, 4, or 5): ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void viewContacts() {
        System.out.println("Contacts:");
        System.out.println("Name           | Phone number");
        System.out.println("-----------------------------");

        for (Contact contact : contacts) {
            System.out.printf("%-15s%n", contact.getName(), contact.getPhoneNumber7());

        }
    }

    private static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        if (isDuplicateName(name)) {
            System.out.print("There's already a contact with the same name. Do you want to overwrite it? (Yes/No): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("No")) {
                System.out.println("Contact not added.");
                return;
            }

            deleteContactByName(name);
        }

        System.out.print("Enter the phone number: ");
        String phoneNumber7 = scanner.nextLine();

        contacts.add(new Contact(name, phoneNumber7));
        System.out.println("Contact added successfully.");
    }

    private static void searchContacts() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();
        List<Contact> searchResults = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                searchResults.add(contact);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No contacts found with the given name.");
        } else {
            System.out.println("Search Results:");
            System.out.println("Name           | Phone number");
            System.out.println("-----------------------------");

            for (Contact contact : searchResults) {
                System.out.printf("%-15s | %s%n", contact.getName(), contact.getPhoneNumber7());
            }
        }
    }

    private static void deleteContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the contact to delete: ");
        String deleteName = scanner.nextLine();

        boolean contactFound = false;

        for (Iterator<Contact> iterator = contacts.iterator(); iterator.hasNext(); ) {
            Contact contact = iterator.next();

            if (contact.getName().equalsIgnoreCase(deleteName)) {
                iterator.remove();
                contactFound = true;
            }
        }

        if (!contactFound) {
            System.out.println("No contact found with the given name.");
        } else {
            System.out.println("Contact deleted successfully.");
        }
    }

    private static void saveContacts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.println(contact);
            }
        } catch (IOException e) {
            System.out.println("Error while saving contacts to file.");
        }
    }

    private static boolean isDuplicateName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    private static void deleteContactByName(String name) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                break;
            }
        }
    }
}
