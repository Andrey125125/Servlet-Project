package model.exeptions;

public class PageNotAccessibleExeption extends RuntimeException {
    public PageNotAccessibleExeption(String message) {
        super(message);
    }
}
