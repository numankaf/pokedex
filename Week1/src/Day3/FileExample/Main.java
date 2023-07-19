package Day3.FileExample;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileManagerOperations fileManagerOperations = new FileManagerOperations();
        //System.out.println(fileManagerOperations.read("Week1/src/Day3/FileExample/a.txt"));
        System.out.println(fileManagerOperations.write("Week1/src/Day3/FileExample/a.txt", "Itadori Yuuji\n"));
        System.out.println(fileManagerOperations.write("Week1/src/Day3/FileExample/b.txt", "Itadori Yuuji\n"));

    }
}
