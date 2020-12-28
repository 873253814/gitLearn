package callback;

public class CallBackMain {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.askQuestion(new CallBack() {
            @Override
            public void execute() {
                super.execute();
            }
        });
    }
}
