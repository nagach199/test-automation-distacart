package com.inmar.automation.utils;

import java.io.File;

public class ExtentReportGenerator {

    public static String getReportConfigPath()
    {
        return new File("./config/extent-config.xml").getAbsolutePath();
    }
}
 