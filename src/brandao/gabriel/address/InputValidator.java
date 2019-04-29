package brandao.gabriel.address;

public class InputValidator {
    
    public static boolean isFieldEmpty(String fieldValue) {
        if (fieldValue == null || fieldValue.length() == 0) return true;
        else return false;
    }
    public static boolean isFieldANumber(String fieldValue) {
        try {
            Integer.parseInt(fieldValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
