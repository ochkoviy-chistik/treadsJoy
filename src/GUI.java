import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GUI extends JFrame {
    private static GUI instance;
    private JPanel mainJPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JLabel label;
    private JTextArea searchArea;
    private JScrollPane scrollArea;

    private GUI(String name) {
        super(name);

        this.setContentPane(mainJPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchButton.addActionListener(new SearchListener());

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static GUI getInstance(String name) {
        if (instance == null) {
            instance = new GUI(name);
        }
        return instance;
    }

    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String searchString = searchField.getText();
            ArrayList<String> responses;

            if (searchString.isEmpty()) {
                searchArea.setText(
                        "Необходимо ввести фамилию!"
                );
            } else {
                SchoolWebParser parser = new SchoolWebParser(searchString);
                searchArea.setText("");

                try {
                    parser.createThreads();
                    responses = parser.getResponse();
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
                for (String url : responses) {
                    searchArea.setText(
                            searchArea.getText() + url + "\n"
                    );
                }

            }
        }
    }
}
