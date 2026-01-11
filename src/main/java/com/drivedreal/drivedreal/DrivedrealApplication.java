package com.drivedreal.drivedreal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class DrivedrealApplication {

	// #region agent log
    private static void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"DrivedrealApplication.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

	public static void main(String[] args) {
        logDebug("DrivedrealApplication main started", null, "B");
		SpringApplication.run(DrivedrealApplication.class, args);
        logDebug("DrivedrealApplication main finished", null, "B");
	}

}
