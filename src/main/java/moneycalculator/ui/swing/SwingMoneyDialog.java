package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;



public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    private final Currency[] currencies;
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
        JTextField textfield= new JTextField();
        textfield.setColumns(10);
        textfield.getDocument().addDocumentListener(amountChanged());
        amount=textfield.getText();
        return textfield;
    }

    private Component currency() {
        JComboBox combo=new JComboBox(currencies);
    /*    currency=currencies[0];
        combo.addItemListener(currencyListener());*/
        combo.addItemListener(currencyChanged());
        currency=(Currency)combo.getSelectedItem();
        return combo;
    }
    
    

    private DocumentListener amountChanged() {
        return new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e){
                amountChanged(e.getDocument());
            }
            
            @Override
            public void removeUpdate(DocumentEvent e){
                amountChanged(e.getDocument());
            }
            
            @Override
            public void changedUpdate (DocumentEvent e){
                amountChanged(e.getDocument());
            }

            private void amountChanged(Document document) {
                try {
                    amount=document.getText(0, document.getLength());
                } 
                catch (BadLocationException ex) {
               
                }
            }   
        };
    }

    private ItemListener currencyChanged() {
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
