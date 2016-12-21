package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;



public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private Currency[] currencies;
    private String amount;
    private Currency currency;
    
    public SwingMoneyDialog(Currency[] currencies){
        this.currencies=currencies;
        
        this.setLayout(new FlowLayout());
        this.add(amount());
        this.add(currency()); 
    }
    
    @Override
    public Money get(){
        return new Money(Double.parseDouble(amount), currency);
    }

    private Component amount() {
        JTextField field= new JTextField();
        field.setColumns(10);
        field.getDocument().addDocumentListener(amountListener());
        return field;
    }

    private Component currency() {
        JComboBox combo=new JComboBox(currencies);
        currency=currencies[0];
        combo.addItemListener(currencyListener());
        return combo;
    }

    private DocumentListener amountListener() {
        return new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e){
                changeAmountWith(e);
            }
            
            @Override
            public void removeUpdate(DocumentEvent e){
                changeAmountWith(e);
            }
            
            @Override
            public void changedUpdate (DocumentEvent e){
                changeAmountWith(e);
            }

            private void changeAmountWith(DocumentEvent e) {
                try {
                    amount=e.getDocument().getText(0,e.getDocument().getLength());
                } catch (BadLocationException ex) {
                }
            }   
        };
    }

    private ItemListener currencyListener() {
        return new ItemListener(){
            
          @Override
          public void itemStateChanged(ItemEvent e){
              if(e.getStateChange()==ItemEvent.SELECTED){
                  currency=(Currency)e.getItem();
              }
          }
        };
    }  
}
