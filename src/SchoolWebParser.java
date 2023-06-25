import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SchoolWebParser {
    private final ArrayList<Thread> threads = new ArrayList<>();
    private final String name;

    public SchoolWebParser(String name) {
        this.name = name;
    }

    public void createThreads() throws URISyntaxException {
        String formatedYear;
        URI uri;

        for (int i = 0; i < 48; ++i) {
            formatedYear = (i + 1) / 10 + Integer.toString((i + 1) % 10);
            uri = new URI(
                    "https://1543.ru/people/%sth_%s/index.htm".formatted(
                            formatedYear,
                            Integer.toString((1975 + i + 1)).substring(2, 4)
                    )
            );

            this.threads.add(new Thread(new WebThread(uri, name) ));
        }
    }

    public void run() {
        for (int i = 0; i < 48; ++i) {
            this.threads.get(i).start();
        }
    }
}
