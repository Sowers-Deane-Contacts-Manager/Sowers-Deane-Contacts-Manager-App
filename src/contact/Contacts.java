package contact;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Scanner;

public class Contacts {

    private String name;

    private String phoneNumber7;

    private String phoneNumber10;


    public void contact(String name, String phoneNumber7, String phoneNumber10) {
        this.name = name;
        this.phoneNumber7 = phoneNumber7 ;
        this.phoneNumber10 = phoneNumber10;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber7() {
        return phoneNumber7;
    }
    public String getPhoneNumber10() {
        return getPhoneNumber10();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return name + (" | " + phoneNumber7 + " |");
    }

}

