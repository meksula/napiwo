package pl.napiwo.scribe.url;

import pl.napiwo.scribe.ScribeApplication;

/**
 * @author
 * Karol Meksuła
 * 07-10-2018
 * */

public enum ApiUrls {
    CERBER_USER_AUTH {
        @Override
        public String getUrl() {
            return ScribeApplication.host + "8015/api/v1/cerber/auth";
        }
    };

    public abstract String getUrl();
}
