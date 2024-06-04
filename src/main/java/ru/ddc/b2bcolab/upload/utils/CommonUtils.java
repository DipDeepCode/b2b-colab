package ru.ddc.b2bcolab.upload.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@Slf4j
public class CommonUtils {
    private CommonUtils() {
        throw new AssertionError("This is a static utility class.");
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }
}
