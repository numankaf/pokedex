package Day3.FileExample;

import java.io.*;

public class FileManagerOperations implements FileManager {


    public FileManagerOperations() {
    }

    @Override
    public String read(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             InputStreamReader reader = new InputStreamReader(fis, "ISO-8859-9")) {
            int t;
            StringBuilder sb = new StringBuilder("");
            while ((t = reader.read()) != -1) {
                char r = (char) t;
                sb.insert(sb.length(), r);
            }
            return sb.toString();

        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String write(String path, String data) {
        try (FileOutputStream o = new FileOutputStream(path, true);
             OutputStreamWriter writer = new OutputStreamWriter(o, "ISO-8859-9")) {
            writer.write(data);
            writer.flush();
            return "Success";

        } catch (Exception e) {
            return "fail";
        }
    }
}
