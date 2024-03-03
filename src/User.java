import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    String surname;
    String name;
    String lastname;
    String birthday;
    Long phone;
    Character sex;

    public User(String surname, String name, String lastname, String birthday, Long phone, Character sex) throws IOException {
        this.surname =surname;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.phone = phone;
        this.sex = sex;
        createUserFile();
    }

    public User(String[] data) throws IOException {
        this(data[0], data[1], data[2], data[3], Long.parseLong(data[4]), data[5].charAt(0));
    }

    private void createUserFile() throws IOException {
        try (FileWriter userDataWriter = new FileWriter(String.format("%s.txt", this.surname), true)) {
            userDataWriter.write(String.format("<%s> <%s> <%s> <%s> <%s> <%s>\n", surname, name, lastname, birthday, phone, sex));
        } catch (IOException e) {
            throw new FileNotFoundException("Ошибка записи в файл");
        }
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("""
                Full name: %s %s %s
                Birthday: %s
                Phone: %s
                Sex: %s""", surname, name, lastname, birthday, phone, sex);
    }
}
