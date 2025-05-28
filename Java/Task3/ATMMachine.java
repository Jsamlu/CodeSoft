
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;

public class ATMMachine implements ActionListener  {

    JFrame frame = new JFrame("ATM Machine");
    JButton balance, withdraw, deposit;
    JTextArea txtField;

    float totBalance;


    public ATMMachine() {

        txtField = new JTextArea(1, 5);
        balance = new JButton("Check Balance");
        deposit = new JButton("Deposit");
        withdraw = new JButton("WithDraw");

        balance.addActionListener(this);
        deposit.addActionListener(this);
        withdraw.addActionListener(this);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(balance);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(txtField);

        frame.setLayout(new FlowLayout());

        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == balance){
            float amt = this.totBalance;
            JOptionPane.showMessageDialog(frame,"Current Balance: "+amt);

        }
        if(e.getSource() == deposit){

            float amt = Float.parseFloat(txtField.getText());
            this.totBalance += amt;
            JOptionPane.showMessageDialog(frame,"Deposited amnount" + amt);
            txtField.setText(" ");

        }
        if(e.getSource() == withdraw){
            float amt = Float.parseFloat(txtField.getText());
            if (this.totBalance >= amt) {
                this.totBalance -= amt;
                JOptionPane.showMessageDialog(frame,"WithDrawn amnount" + amt);
                txtField.setText(" ");
            }
            else{
                JOptionPane.showMessageDialog(frame,"insufficient amnount");

            }
        }
    }

    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();
    }

}
