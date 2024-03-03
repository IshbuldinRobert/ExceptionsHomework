import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String newData = inputData("Введите общие данные (ФИО (полностью), д/р (dd.mm.yyyy), тел. (11 цифр), пол (f/m)) через пробел: ");
        User user1 = createUser(newData);
        System.out.println(user1);
    }

    public static String inputData(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextLine();
    }

    public static User createUser(String data) throws IOException {
        // check amount data
        String[] lines = data.split(" ");
        if (lines.length != 6) throw new AmountInputDataException();
        // check ФИО
        for (int i = 0; i < lines.length - 3; i++) {
            char[] chars = lines[i].toCharArray();
            for (char aChar : chars) {
                if (!Character.isLetter(aChar)) throw new RuntimeException("Неверный формат ФИО");
            }
        }
        // check birthday
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            format.setLenient(false);
            format.parse(lines[3]);
        } catch (Exception e) {
            throw new RuntimeException("Неверный формат даты рождения");
        }
        // check phone number
        try {
            if (lines[4].length() != 11) throw new RuntimeException("Неверный формат телефона (нужно 11 цифр)");
            Long.parseLong(lines[4]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Номер телефона должен быть в формате одного числа");
        }
        // check sex
        if (!(lines[5].equalsIgnoreCase("m") || lines[5].equalsIgnoreCase("f")))
            throw new RuntimeException("Неверный пол (f - жен, m - муж)");

        return new User(lines);
    }
}

class AmountInputDataException extends IOException {
    public AmountInputDataException() {
        super("Количество входных данных неверно (нужно 6 полей через пробел)");
    }
}