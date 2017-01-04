package moneycalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.control.Command;
import moneycalculator.model.Currency;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.ui.swing.SwingMoneyDialog;
import moneycalculator.ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame{
    
    private final Currency[] currencies;
    private final Map<String, Command> commands=new HashMap<>();
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    
    public MainFrame(Currency[] currencies){
        this.currencies=currencies;
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setMinimumSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); 
        this.add(moneyDialog(),BorderLayout.NORTH);
        this.add(moneyDisplay(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    public void add(Command command){
        commands.put(command.name(), command);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
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
        JButton button=new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent e){
                commands.get("calculate").execute();
            }
        };
    }   

    private Component toolbar() {
        JPanel panel=new JPanel();
        panel.add(calculateButton());
        return panel;
    }
}
