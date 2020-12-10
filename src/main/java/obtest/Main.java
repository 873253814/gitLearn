package obtest;

public class Main {
    public static void main(String[] args) {
        ObEvent event = new ObEvent();
        Subject subject = new Subject();
        subject.attach(event);
        subject.update();
    }
}
