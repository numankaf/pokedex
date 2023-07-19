package Day3.BufferedReader;

public class Main {
    public static void main(String[] args){
        BufferedReaderManager brm = new BufferedReaderManager();
        System.out.println(brm.write("Week1/src/Day3/BufferedReader/a.txt", "Itadori Yuuji\n"));
        System.out.println(brm.write("Week1/src/Day3/BufferedReader/a.txt", "Kgusaki Nobara\n"));
        System.out.println(brm.read("Week1/src/Day3/BufferedReader/a.txt"));
    }
}
