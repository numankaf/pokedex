package Day4.SerilazeExample2;

import java.io.*;

public class First {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Course c1 = new Course("BBM101");
        Student s1 = new Student("Gojo Satoru",1,"Computer Science", c1);
        FileOutputStream fos = new FileOutputStream("Week1/src/Day4/SerilazeExample2/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s1);

        FileInputStream fis = new FileInputStream("Week1/src/Day4/SerilazeExample2/a.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student readed1 = (Student) ois.readObject();
        System.out.println(readed1);
    }
}
