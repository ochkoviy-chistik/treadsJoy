import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;

class WebThread implements Runnable {
    private final URI uri;
    private final String name;
    private String response;
    private final Thread thread;

    WebThread(URI uri, String name) {
        this.uri = uri;
        this.name = name;

        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        try {
            URLConnection connection = uri.toURL().openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "windows-1251") );

            String s;
            StringBuilder htmlResponse = new StringBuilder();

            while ((s = br.readLine()) != null) {
                htmlResponse.append(s);
            }
            // htmlResponse = new StringBuilder(new String(htmlResponse.toString().getBytes(), "Cp1251"));
            if (htmlResponse.toString().contains(name)) {
                this.response = String.valueOf(uri);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Thread getThread() {
        return thread;
    }

    public String getResponse() {
        return this.response;
    }
}
