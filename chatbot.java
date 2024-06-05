import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Scanner;

public class Chatbot {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! I'm your console chatbot. How can I assist you today?");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
    }

    private static void handleInput(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else if (input.toLowerCase().startsWith("open application")) {
            String application = input.substring("open application".length()).trim();
            openApplication(application);
        } else if (input.toLowerCase().startsWith("search")) {
            String query = input.substring("search".length()).trim();
            searchWeb(query);
        } else {
            System.out.println("I didn't understand that command.");
        }
    }

    private static void openApplication(String application) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("cmd /c start " + application);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open -a " + application);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + application);
            }
            System.out.println("Opening " + application);
        } catch (Exception e) {
            System.out.println("Sorry, I couldn't open the application: " + e.getMessage());
        }
    }

    private static void searchWeb(String query) {
        try {
            String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("Searching for: " + query);
            } else {
                System.out.println("Desktop is not supported. Can't perform the search.");
            }
        } catch (Exception e) {
            System.out.println("Sorry, I couldn't perform the search: " + e.getMessage());
        }
    }
}
