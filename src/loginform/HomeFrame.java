package loginform;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomeFrame extends JFrame {

    JLabel message;
    Container container = getContentPane();

    public HomeFrame() {
        message = new JLabel("You are successfully logged in!");
        container.setLayout(null);
        this.setTitle("Home Form");
        this.setVisible(true);
        this.setBounds(250, 250, 370, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setBounds();
        addComponents();
    }

    private void setBounds() {
        message.setBounds(10, 10, 400, 30);
    }

    private void addComponents() {
        container.add(message);
    }
}
