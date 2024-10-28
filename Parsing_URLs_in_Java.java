//Objective: Learn how to create and parse URLs using the java.net.URL class

//Steps:
//        1. Create a new Java class named URLParsingExample.
//        2. Write code to parse the following URL:
//        https://user:password@www.example.com:8080/path/to/resource.html?
//        query=java&sort=asc#section1
//        3. Extract and print each component of the URL: protocol, user info, host, port, path,
//        query, fragment, and authority.

import java.net.MalformedURLException;
import java.net.URL;
public class Parsing_URLs_in_Java {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://user:password@www.example.com:8080/path/to/resource.html?query=java#section1");
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("User Info: " + url.getUserInfo());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("Fragment (Ref): " + url.getRef());
            System.out.println("Authority: " + url.getAuthority());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
