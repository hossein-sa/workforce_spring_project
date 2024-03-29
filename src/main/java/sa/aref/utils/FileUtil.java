package sa.aref.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Component
public class FileUtil {

    public static byte[] imageConverter(File file) {
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] imageByte = fileInputStream.readAllBytes();
                fileInputStream.close();
                return imageByte;
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static File fileWriter(Path path, byte[] bytes) throws IOException {
        String randomCode = "/" + String.valueOf((int) (Math.random() * (999 - 100 + 1) + 100)) + ".jpg";
        path = Path.of(path.toString() + randomCode);
        FileOutputStream fos = new FileOutputStream(path.toFile());
        fos.write(bytes);
        return new File(path.toUri());
    }

    public static void byteArrayToFile(byte[] bytes, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }
}
