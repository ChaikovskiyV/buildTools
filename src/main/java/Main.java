import reader.ReaderFromFile;

import service.Calculation;
import validation.DataValidation;

public class Main {
    public static void main(String[] args) {
        String fileName = "numbers.txt";
        ReaderFromFile reader = new ReaderFromFile(fileName);
        double numberOne = new DataValidation(reader.readArrayFromFile()[0]).getNumber();
        double numberTwo = new DataValidation(reader.readArrayFromFile()[1]).getNumber();

        System.out.println("The results of calculations with numbers "+ numberOne+" and "+numberTwo+" are:"+"\n"+
        "- additional: "+ Calculation.addition(numberOne, numberTwo)+
        "\n- subtraction: "+ Calculation.subtraction(numberOne, numberTwo)+
                "\n- division: "+ Calculation.division(numberOne, numberTwo)+
                "\n- multiplication: "+ Calculation.multiplication(numberOne, numberTwo));
    }
}
