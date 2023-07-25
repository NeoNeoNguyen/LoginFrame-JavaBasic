package loginform;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.mindrot.jbcrypt.BCrypt;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel userNameLable, passwordLAbel;
    JTextField userNameTextField;
    JPasswordField passwordField;
    JButton loginButton;
    Container container;

    public LoginFrame() {
        userNameLable = new JLabel("User Name");
        userNameTextField = new JTextField();
        passwordLAbel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListenner();
    }

    private void setBounds() {
        userNameLable.setBounds(10, 10, 100, 30);
        userNameTextField.setBounds(100, 10, 200, 30);
        passwordLAbel.setBounds(10, 50, 100, 30);
        passwordField.setBounds(100, 50, 200, 30);
        loginButton.setBounds(100, 100, 200, 30);
    }

    private void addComponents() {
        container.add(userNameLable);
        container.add(userNameTextField);
        container.add(passwordLAbel);
        container.add(passwordField);
        container.add(loginButton);
    }

    private void addActionListenner() {
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Even called");
        if (e.getSource() == loginButton) {
            String userName = userNameTextField.getText();
            String password = passwordField.getText();
            System.out.println(userName + "" + password);

            Validation v = new Validation();
            java.util.List<String> errors = v.validateLogin(userName, password);
            if (errors.size() > 0) {
                JOptionPane.showMessageDialog(null, errors.toArray());
                return;
            }
//            if (userName.equalsIgnoreCase("Test") && password.equalsIgnoreCase("123@.")) {
//                System.err.println("Logged in");
//            } else {
//                System.out.println("Unable to login");
//            }

            //check database
            LoginDAO dao = new LoginDAO();
            Student student = dao.checkLogin(userName);
            System.out.println(student);
            if (student.getId() == 0) {
                System.out.println("No user found with username");
                return;
            }
            if (BCrypt.checkpw(password, student.getPassword())) {
                System.out.println("Logged in");
                new HomeFrame().setVisible(true);
                this.dispose();
            } else {
                System.out.println("Unable to login");
                JOptionPane.showMessageDialog(null, "User id or password is incorrect");
                return;
            }
        }
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(250, 250, 370, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
