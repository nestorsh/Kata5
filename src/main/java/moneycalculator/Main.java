package moneycalculator;

import moneycalculator.model.Currency;

public class Main {
    public static void main(String[] args){
        new MainFrame(currencies());
    }
    private static Currency[] currencies(){
        return new Currency[]{
            new Currency("USD","Dolar USA","$"),
            new Currency("CAD","Dolar de Canada","$"),
            new Currency("GBP","Libra esterlina","£")
        };
    }
}
