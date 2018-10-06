package pl.napiwo.cerberdb.exception

import spock.lang.Specification

/**
 * @author
 * Karol Meksuła
 * 06-10-2018
 * */

class CerberdbDataNotFoundTest extends Specification {
    def entityName = "CerberdbDataNotFound.class"
    def id = "023=2d=2d23d/3=2d023jn19dm0"

    def "throw exception and test message content"() {
        expect:
        def message = new CerberdbDataNotFound(entityName, id).getMessage()
        message == "Cannot found CerberdbDataNotFound.class with id: [023=2d=2d23d/3=2d023jn19dm0] in database."
    }
}
