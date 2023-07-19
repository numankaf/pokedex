package Day3.BufferedReader;

import Day3.FileExample.FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferedReaderManager implements FileManager {
    @Override
    public String read(String path) {
        try(FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr)){
            StringBuilder s = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                s.append(line);
                s.append(System.lineSeparator());
            }

            return s.toString();

        }
        catch (Exception e){
            return "Error";
        }
    }

    @Override
    public String write(String path, String data) {
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(data);
            return "Success";
        }
        catch (Exception e){
            return "Error";
        }

    }
}
