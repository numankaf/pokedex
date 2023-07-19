package Day3.FileReaderWriterExample;

;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("Week1/src/Day3/FileReaderWriterExample/example.txt");
        FileReader fr = new FileReader("Week1/src/Day3/FileReaderWriterExample/example.txt");
        char[] chars = new char[(int) file.length()];
        fr.read(chars);
        System.out.println(chars);
        fr.close();

//        FileWriter fw = new FileWriter("Week1/src/Day3/FileReaderWriterExample/example.txt", false);
//        fw.write("Itadori Yuuji");
//        fw.close();
    }
}
