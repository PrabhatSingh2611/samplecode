package utils;

import lombok.Data;
import java.io.*;
import java.util.Properties;

@Data
public class Config {

    private final String propertyFilePath = "src/main/resources/audra.aqa.properties";
    private String baseURL;
    private String apiURL;
    private String testDataDirectory;
    private String downloadFolder;
    private String testImageJpeg;

    Properties properties = new Properties();

    public Config(){
        try {
            FileInputStream fileInputStream = new FileInputStream(propertyFilePath);
            properties.load(fileInputStream);

            this.baseURL = properties.getProperty("base.url");
            this.apiURL = properties.getProperty("api.url");
            this.testDataDirectory = properties.getProperty("test.data.directory");
            this.downloadFolder = properties.getProperty("download.folder");
            this.testImageJpeg = properties.getProperty("image.jpeg");

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propName) {
        try {
            InputStream input = new FileInputStream(propertyFilePath);
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties.getProperty(propName);
    }

    /**
     * @param fileName : File name to located in Config folder
     * @param propName : Property name to retrieve value from fileName
     * @return String : Value of property in config.properties
     */
    public String getProperty(String fileName, String propName) {
        try {
            InputStream input = new FileInputStream(
                    System.getProperty("user.dir") + File.separator + "Config" + File.separator + fileName);
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return properties.getProperty(propName);
    }
}
