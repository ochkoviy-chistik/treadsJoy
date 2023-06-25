import java.net.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        SchoolWebParser schoolWebParser = new SchoolWebParser(name);
        schoolWebParser.createThreads();
        schoolWebParser.run();
    }
}
