package objectCreater;

import number.CustomNumber;
import validation.DataValidation;
import validation.DataValidation;

public class NumbersFactory {
    private String str;
    private DataValidation checkData;

    public NumbersFactory(String str){
        this.str = str;
        checkData = new DataValidation(str);
    }

    public CustomNumber createNumber(){
        return new CustomNumber(checkData.getNumber());
    }

}