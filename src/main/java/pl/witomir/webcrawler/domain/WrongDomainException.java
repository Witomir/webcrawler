package pl.witomir.webcrawler.domain;

public class WrongDomainException extends RuntimeException {
    public WrongDomainException(Exception e) {
        super(e);
    }
}
