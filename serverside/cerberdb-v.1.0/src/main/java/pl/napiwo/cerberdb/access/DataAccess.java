package pl.napiwo.cerberdb.access;

import pl.napiwo.cerberdb.dto.UserAccessKey;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

public interface DataAccess {
    boolean isAuthorized(UserAccessKey userAccessKey);
}
