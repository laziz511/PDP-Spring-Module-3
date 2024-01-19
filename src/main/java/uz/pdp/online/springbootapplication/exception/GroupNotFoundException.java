package uz.pdp.online.springbootapplication.exception;

public class GroupNotFoundException extends NotFoundException {

    public GroupNotFoundException(String message) {
        super(message);
    }
}