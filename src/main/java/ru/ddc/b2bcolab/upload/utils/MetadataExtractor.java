package ru.ddc.b2bcolab.upload.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.Imaging;
import org.springframework.core.io.Resource;
import ru.ddc.b2bcolab.upload.model.Directory;
import ru.ddc.b2bcolab.upload.model.DirectoryType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MetadataExtractor {

    private MetadataExtractor() {
    }

    public static List<Directory> getFileMetadata(Resource resource) {

        final List<Directory> directories = new ArrayList<>();

        try {
            BasicFileAttributes attr = Files.readAttributes(resource.getFile().toPath(), BasicFileAttributes.class);
            directories.add(new Directory(DirectoryType.FILE, "File Name", resource.getFilename()));
            directories.add(new Directory(DirectoryType.FILE, "File Size",
                    String.valueOf(CommonUtils.humanReadableByteCountSI(attr.size()))));
            directories.add(new Directory(DirectoryType.FILE, "File Modification Date/Time",
                    String.valueOf(attr.creationTime())));
            directories.add(new Directory(DirectoryType.FILE, "File Creation Date/Time",
                    String.valueOf(attr.lastModifiedTime())));
        }
        catch (FileNotFoundException ex) {
            return directories;
        }
        catch (IOException ex) {
            throw new IllegalStateException(
                    "An I/O error occurred while retrieving the file attributes for file " + resource.getFilename(),
                    ex);
        }
        return directories;
    }

    public static ImageInfo loadImageInfo(byte[] imageBytes) {
        final ImageInfo imageInfo;

        try {
            imageInfo = Imaging.getImageInfo(imageBytes);
        }
        catch (ImageReadException | IOException ex) {
            throw new IllegalStateException("Unable to read 'image info' of the provided image data.", ex);
        }
        return imageInfo;
    }
}
