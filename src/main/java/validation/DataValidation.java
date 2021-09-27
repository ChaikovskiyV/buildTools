package validation;

import exception.WrongDataExeption;

public class DataValidation {
    private String string;
    private Double number;

    public DataValidation(String str) {
        this.string = str;

        if(checkData()) {
            number = Double.valueOf(string);
        } else if(corectData(string)){
            number = Double.valueOf(string);
        } else {
            try {
                throw new WrongDataExeption("The file includes wrong data");
            } catch (WrongDataExeption e){
                e.printStackTrace();
            }
        }
    }

    public DataValidation(){}

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getNumber() {
        return number;
    }

    public boolean checkData(){
        boolean checkResult = false;

        try {
            if (Double.valueOf(string) instanceof Double) {
                checkResult = true;
            }
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return checkResult;
    }

    public boolean corectData(String wrongString){
        boolean dataWasCorected = false;
        int pointsNumber = 0;
        StringBuilder stringBuilder = new StringBuilder(wrongString);
        for(int i = 0; i < stringBuilder.length(); i++){
            if(pointsNumber < 1 && stringBuilder.charAt(i) == '.'){
                pointsNumber++;
            } else if(!Character.isDigit(stringBuilder.charAt(i))){
                stringBuilder.deleteCharAt(i);
                i--;
            }
        }
        if(!stringBuilder.isEmpty()){
            string = stringBuilder.toString();
            dataWasCorected = true;
        }
        return dataWasCorected;
    }
}