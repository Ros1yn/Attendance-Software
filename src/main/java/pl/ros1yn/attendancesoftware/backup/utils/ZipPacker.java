package pl.ros1yn.attendancesoftware.backup.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ZipPacker {

    public static void addAllFilesToZip(FileOutputStream fos, List<String> filesToZip) throws IOException {

        try (ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            for (String srcFile : filesToZip) {
                File fileToZip = new File(srcFile);

                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
        }
    }
}
