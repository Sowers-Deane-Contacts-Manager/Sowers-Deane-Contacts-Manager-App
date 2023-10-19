package contact;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsManagerCLI {
    private static List<Contact> contacts;
    private static final String FILE_NAME = "src/data/contacts.txt";

    public static void main(String[] args) throws IOException {

        boolean exit = false;
        while (!exit) {
        contacts = loadContacts();

            int choice = displayMainMenu();

            switch (choice) {
                case 1 -> viewContacts();
                case 2 -> addContact();
                case 3 -> searchContacts();
                case 4 -> deleteContact();
                case 5 -> exit = true;
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
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
//print main menu
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
//1. view contacts
    private static void viewContacts() {
        loadContacts();
        System.out.println("Contacts:");
        System.out.println("Name           | Phone number");
        System.out.println("-----------------------------");

        for (Contact contact : contacts) {
            System.out.printf("%-15s%n", contact.getName(), contact.getPhoneNumber7());

        }
    }
//2.add contacts
    private static void addContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

//bonus checks for duplicate names and asks if you want to overwrite
        boolean saveName = isDuplicateName(name);
        if (saveName) {
            System.out.println("There's already a contact with the same name. Do you want to overwrite it? (Yes/No): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("No")) {
                System.out.println("Contact not added.");
                return;
            }

            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Contact will now be overwritten.");
            }
            deleteContact(name);
        }


        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();
        phoneNumber = formatPhoneNumber(phoneNumber);
        String addContact = (name + " " + phoneNumber);
        Files.write(
                Paths.get("src/data", "contacts.txt"),
                Collections.singletonList(addContact),
                StandardOpenOption.APPEND
        );
        System.out.println("Contact added successfully.");
        loadContacts();
    }


//3. search contacts
    private static void searchContacts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name to search: ");
        String searchName = scanner.nextLine();


        Path filePath = Paths.get(FILE_NAME);
        List<String> fileInfo = Files.readAllLines(filePath);

        boolean found = false;
        for (int i = 0; i < fileInfo.size(); i++) {
            if (fileInfo.get(i).contains(searchName)) {
                System.out.println(fileInfo.get(i));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Not Found");
        }
    }
//4. delete contacts with IOExeption
    private static void deleteContact() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the contact to delete: ");
        String deleteName = scanner.nextLine();

        Path filePath = Paths.get(FILE_NAME);
        List<String> fileInfo = Files.readAllLines(filePath);

        for(int i = 0; i < fileInfo.size(); i++) {
            if (fileInfo.get(i).contains(deleteName)) {
                fileInfo.remove(fileInfo.get(i));
            }
        }
        Files.write(
                Paths.get("src/data", "contacts.txt"),
                fileInfo
        );
    }

//    from isDuplicateName allows user to delete a contact by name if users want to
    private static void deleteContact(String name) throws IOException {
        Path filePath = Paths.get(FILE_NAME);
        List<String> fileInfo = Files.readAllLines(filePath);

        for(int i = 0; i < fileInfo.size(); i++) {
            if (fileInfo.get(i).contains(name)) {
                fileInfo.remove(fileInfo.get(i));
            }
        }
        Files.write(
                Paths.get("src/data", "contacts.txt"),
                fileInfo
        );
    }
//saves new contacts to file
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
            if (contact.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }
//bonus format 7 and 10 digit phone numbers with a dash
private static String formatPhoneNumber(String phoneNumber) {
    String justNumbers = phoneNumber.replaceAll("[^0-9]", "");

    if (justNumbers.length() == 7 || justNumbers.length() == 10) {
        if (justNumbers.length() == 7) {
            return justNumbers.replaceFirst("(\\d{3})(\\d{4})", "$1-$2");
        } else {
            return justNumbers.replaceFirst("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
        }
    } else {
        return phoneNumber;
    }
}
}
