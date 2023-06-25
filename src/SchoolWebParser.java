import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SchoolWebParser {
    private final ArrayList<WebThread> threads = new ArrayList<>();
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

            threads.add(new WebThread(uri, name) );
        }
    }

    public boolean isFinished() {
        for (WebThread webThread : threads) {
            if (webThread.getThread().getState() == Thread.State.RUNNABLE) return false;
        }
        return true;
    }

    public ArrayList<String> getResponse() {
        ArrayList<String> responseList = new ArrayList<>();
        String response;

        int i = 0;

        while (i < 48) {
            if (threads.get(i).getThread().getState() == Thread.State.TERMINATED) {
                response = threads.get(i).getResponse();

                if (response != null ) responseList.add(response);

                i++;
            }
        }

        return responseList;
    }
}
