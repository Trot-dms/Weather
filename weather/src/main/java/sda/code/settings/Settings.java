package sda.code.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public enum Settings {
    CONFIG;

    private final Properties props;
    private InputStream config = null;

    private Settings() {
        props = new Properties();
        config = Settings.class.getResourceAsStream("/default.conf");
        try {
            props.load(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getApiId() {
        return Optional.ofNullable(props.getProperty(SettingsDefaults.APPLICATION_ID.getValue()))
                .orElse(SettingsDefaults.DEFAULT_APP_ID_VAL.getValue()).toString();
    }

    public String getAddress() {
        return Optional.ofNullable(props.getProperty(SettingsDefaults.ADDRESS.getValue()))
                .orElse(SettingsDefaults.DEFAULT_ADDRESS_VALUE.getValue()).toString();
    }

    public String getDefaultCity() {
        return Optional.ofNullable(props.getProperty(SettingsDefaults.DEFAULT_CITY.getValue()))
                .orElse(SettingsDefaults.DEFAULT_CITY_VALUE.getValue()).toString().toLowerCase();
    }

    public String getDefaultLanguage() {
        String lang = Optional.ofNullable(props.getProperty(SettingsDefaults.DEFAULT_LANG.getValue()))
                .orElse(SettingsDefaults.DEFAULT_LANG_VALUE.getValue()).toString().toLowerCase();
        switch (lang) {
            case "polish":
                return Language.POLISH.setLanguage().toLowerCase();

            case "german":
                return Language.GERMAN.setLanguage().toLowerCase();

            default:
                return Language.ENGLISH.setLanguage().toLowerCase();
        }
    }

    public String getDefaultUnits() {
        String units = Optional.ofNullable(props.getProperty(SettingsDefaults.DEFAULT_UNITS.getValue()))
                .orElse(SettingsDefaults.DEFAULT_UNITS_VAL.getValue()).toString().toLowerCase();
        switch (units) {
            case "imperial":
                return Units.IMPERIAL.toString().toLowerCase();

            default:
                return Units.METRIC.toString().toLowerCase();
        }
    }
}
