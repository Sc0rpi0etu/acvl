package acvl.tools;

public class TextVerifier {
    public static boolean isName(String field) {
        return field.matches("[A-z]+(( |-)[A-z]*('[A-z]+)*)*") && !field.equals("");
    }

    public static boolean isEmail(String field) {
        return field.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}") && !field.equals("");
    }

    public static boolean isAdress(String field) {
        return field.matches("([0-9]*) ?([a-zA-Z,\\. ]*) ?([0-9]{5}) ?([a-zA-Z]*)") && !field.equals("");
    }

    public static boolean hasSpace(String field) {
        return field.matches("(.* .*)+") || !field.equals("");
    }
}
