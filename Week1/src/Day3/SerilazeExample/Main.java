package Day3.SerilazeExample;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AnimeCharacter c1 = new AnimeCharacter("Itadori Yuji", "Jujutsu Kaisen", 17);
        AnimeCharacter c2 = new AnimeCharacter("Ryomen Sukuna", "Jujutsu Kaisen", 456);
        FileOutputStream fos = new FileOutputStream("Week1/src/Day3/SerilazeExample/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(c1);
        oos.writeObject(c2);

        FileInputStream fis = new FileInputStream("Week1/src/Day3/SerilazeExample/a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        AnimeCharacter readed1 = (AnimeCharacter) ois.readObject();
        System.out.println(readed1);
        AnimeCharacter readed2 = (AnimeCharacter) ois.readObject();
        System.out.println(readed2);


    }
}
