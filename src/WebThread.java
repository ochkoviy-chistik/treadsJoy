import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;

class WebThread implements Runnable {
    private final URI uri;
    private final String name;

    WebThread(URI uri, String name) {
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
