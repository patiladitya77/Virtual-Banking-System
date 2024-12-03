import javax.swing.*;
import java.awt.*;

class Home extends JFrame {
    Home(String username) {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Welcome " + username, JLabel.CENTER);
        JButton b1 = new JButton("Logout");

        title.setFont(f);
        b1.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        title.setBounds(100, 30, 600, 50);
        b1.setBounds(300, 150, 200, 40);

        c.add(title);
        c.add(b1);

        b1.addActionListener(
                a->{
                    new Landing();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");
    }

    public static void main(String[] args) {
        new Home("Your Name");
    }
}
