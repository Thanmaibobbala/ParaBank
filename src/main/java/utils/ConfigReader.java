package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public ConfigReader() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(prop.getProperty("timeout"));
    }
}