import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        ArrayList<Thread> threads = new ArrayList<>();
        URI uri;
        String formatedYear;

        for (int i = 0; i < 48; ++i) {
            formatedYear = (i + 1) / 10 + Integer.toString((i + 1) % 10);
            uri = new URI("https://1543.ru/people/%sth_%s/index.htm".formatted(
                    formatedYear, Integer.toString((1975 + i + 1)).substring(2, 4)
            )
            );
            threads.add(new Thread(new WebThread(uri, name) ));
        }

        for (int i = 0; i < 48; ++i) {
            threads.get(i).start();
        }
    }
}
