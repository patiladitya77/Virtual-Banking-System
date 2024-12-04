import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

class Nlogin extends JFrame {
    Nlogin() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Set Username");
        JTextField t1 = new JTextField(10);

        JLabel l2 = new JLabel("Set Password");
        JTextField t2 = new JTextField(10);

        JLabel l3 = new JLabel("Confirm Password");
        JTextField t3 = new JTextField(10);

        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        JLabel title = new JLabel("Signup", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(300, 10, 200, 40);

        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f2);
        l3.setFont(f2);
        t3.setFont(f2);
        b2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        l1.setBounds(300, 50, 200, 30);
        t1.setBounds(300, 90, 200, 30);

        l2.setBounds(300, 140, 200, 30);
        t2.setBounds(300, 180, 200, 30);

        l3.setBounds(300, 230, 200, 30);
        t3.setBounds(300, 270, 200, 30);

        b1.setBounds(300, 320, 200, 40);
        b2.setBounds(300, 380, 200, 40);

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l3);
        c.add(t3);
        c.add(b1);
        c.add(b2);

        b1.addActionListener(
                a->{
                    if(t2.getText().equals(t3.getText())){
                        String url = "jdbc:mysql://localhost:3306/batch2";
                        try(Connection con = DriverManager.getConnection(url,"root","adpatil@05")){
                            String sql = "insert into users(username,password) values(?,?)";
                            try(PreparedStatement pst = con.prepareStatement(sql)){
                                pst.setString(1,t1.getText());
                                pst.setString(2,t2.getText());

                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Successfull");
                                new Home(t1.getText());
                                dispose();
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"User already exists");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Password should match");
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new Landing();
                    dispose();
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Signup");
    }

    public static void main(String[] args) {
        Nlogin a = new Nlogin();
    }
}
