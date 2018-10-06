package pl.napiwo.cerberdb.exception;

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

public class CerberdbDataNotFound extends RuntimeException {
    private String message;

    public CerberdbDataNotFound(String entityName, String id) {
        this.message = messageBuild(entityName, id);
    }

    private String messageBuild(String entityName, String id) {
        return "Cannot found " + entityName + " with id: [" + id + "] in database.";
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
