package DriverUtils;

import java.io.IOException;

public class ExecuteAdbCommandUtil {
    public static void executeAdbCommand(String command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", command);
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
