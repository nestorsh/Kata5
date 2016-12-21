package moneycalculator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.model.Currency;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.ui.swing.SwingMoneyDialog;
import moneycalculator.ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame{
    private Currency[] currencies;
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    
    public MainFrame(Currency[] currencies){
        this.currencies=currencies;
        setTitle("Money Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setMinimumSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); 
        add(moneyDialog());
        add(moneyDisplay());
        add(calculateButton());
        setVisible(true);
    }

    private Component moneyDialog() {
        SwingMoneyDialog dialog=new SwingMoneyDialog(currencies);
        this.moneyDialog=dialog;
        return dialog;
    }
    
    private Component moneyDisplay(){
        SwingMoneyDisplay display=new SwingMoneyDisplay();
        this.moneyDisplay=display;
        return display;
    }

    private Component calculateButton(){
        JPanel panel=new JPanel(new FlowLayout());
        JButton button=new JButton("Exchange");
        button.addActionListener(exchange());
        panel.add(button);
        return panel;
    }

    private ActionListener exchange() {
        return new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent e){
                moneyDisplay.display(moneyDialog.get());
            }
        };
    }   
}
