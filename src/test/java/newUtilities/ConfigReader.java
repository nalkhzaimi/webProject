package newUtilities;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();
    private static final BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
            textEncryptor.setPassword(
                System.getenv("CONFIG_ENCRYPTION_KEY") != null
                    ? System.getenv("CONFIG_ENCRYPTION_KEY")
                    : "password"
            );
        } catch (IOException e) {
            throw new RuntimeException("config.properties not found", e);
        }
    }

    /**
     * Resolution order: env var → system property → config.properties (with Jasypt decryption)
     */
    public static String get(String key) {
        String envKey = key.toUpperCase().replace(".", "_").replace("-", "_");
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) return envValue;

        String sysValue = System.getProperty(key);
        if (sysValue != null && !sysValue.isBlank()) return sysValue;

        String value = props.getProperty(key);
        if (value == null) throw new RuntimeException("Config key not found: " + key);
        if (value.startsWith("ENC(") && value.endsWith(")")) {
            return textEncryptor.decrypt(value.substring(4, value.length() - 1));
        }
        return value;
    }
}
