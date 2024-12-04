import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Deposit extends JFrame
{
    Deposit(String username)
    {

        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Deposit Money", JLabel.CENTER);
        JLabel label = new JLabel("Enter Amount:");
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Deposit");
        JButton b2 = new JButton("Back");

        title.setFont(f);
        label.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);

        title.setBounds(200, 30, 400, 50);
        label.setBounds(250, 120, 300, 30);
        t1.setBounds(250, 160, 300, 30);
        b1.setBounds(300, 220, 200, 40);
        b2.setBounds(300, 280, 200, 40);

        c.add(title);
        c.add(label);
        c.add(t1);
        c.add(b1);
        c.add(b2);

        b2.addActionListener(
                a->{
                    new Home(username);
                    dispose();
                }
        );

        b1.addActionListener(
                a->{
                    double balance = 0.0;
                    double total = 0.0;

                    //part 1:-fetch balance from database
                    String url = "jdbc:mysql://localhost:3306/batch2";
                    try(Connection con = DriverManager.getConnection(url,"root","adpatil@05")){
                        String sql = "select balance from users where username=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setString(1,username);

                            ResultSet rs = pst.executeQuery();
                            if(rs.next()){
                                balance = rs.getDouble("balance");
                            }

                        }

                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }

                    //part 2:-take amount from user
                    String s1 =t1.getText();
                    if(s1.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please enter amount");
                    }
                    else {
                        double amount = Double.parseDouble(s1);
                        total = amount + balance;
                    }

                    //part 3:- update balance in database
                    try(Connection con = DriverManager.getConnection(url,"root","adpatil@05")){
                        String sql = "update users set balance =? where username=?";
                        try(PreparedStatement pst = con.prepareStatement(sql)){
                            pst.setDouble(1,total);
                            pst.setString(2,username);
                            pst.executeUpdate();

                            JOptionPane.showMessageDialog(null,"Successfully Deposited");
                            t1.setText(""); //to empty the textfield after deposition
                        }

                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }

                }
        );


        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Deposit Money");
    }

    public static void main(String[] args) {
        new Deposit("aditya");
    }
}