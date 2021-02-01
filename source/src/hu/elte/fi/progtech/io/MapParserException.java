package hu.elte.fi.progtech.io;

public class MapParserException extends Exception {

    public MapParserException(String message, Throwable throwable, String filePath) {
        super(message + " <File path: " + filePath + ">", throwable);
    }

    public MapParserException(String message) {
        super(message);
    }

    public MapParserException(String message, String elementName) {
        this(message + " (element: " + elementName + ")");
    }

    public MapParserException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
