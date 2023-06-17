import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class MyThread implements Runnable {
    URI uri;
    private final String name;

    public MyThread(URI uri, String name) {
        this.uri = uri;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            URLConnection connection = this.uri.toURL().openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251") );

            String s;
            StringBuilder htmlResponse = new StringBuilder();

            while ((s = br.readLine()) != null) {
                htmlResponse.append(s);
            }
            // htmlResponse = new StringBuilder(new String(htmlResponse.toString().getBytes(), "Cp1251"));
            if (htmlResponse.toString().contains(this.name)) {
            System.out.println(this.uri);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


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
            threads.add(new Thread(new MyThread(uri, name) ));
        }

        for (int i = 0; i < 48; ++i) {
            threads.get(i).start();
        }
    }
}
