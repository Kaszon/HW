package model;

import java.util.Calendar;
import java.util.Date;

public final class DataChecker {
    
    private DataChecker(){
        
    }
    
    public static boolean isOkLicenceNumber(String licenseNumber){
        boolean result = true;
        if (licenseNumber.length() == 6){
            for (int i = 0; i < 3; i++) {
                result = Character.isLetter(licenseNumber.charAt(i)) && result;
                result = Character.isDigit(licenseNumber.charAt(i+3)) && result;
            }
            return result;
        }
        else{
            return false;
        }
    }
    
    public static boolean isOkLastService(Date lastService){
        return !(lastService.after(new Date()));
    }
    
    public static boolean isOkYearOfManufacture(int year){
        return year > 1920 && year <= Calendar.YEAR;
    }
    
    public static boolean isOkPricePerDay(int price){
        return price > 3000;
    }
    
}
