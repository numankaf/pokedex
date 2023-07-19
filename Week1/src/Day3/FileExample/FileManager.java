package Day3.FileExample;

public interface FileManager {
    String read(String path);

    String write(String path, String data);
}
