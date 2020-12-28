package callback;

public class Teacher {
    private String name;

    private String question;

    public void askQuestion(CallBack callBack) {
        System.out.println("ask Question");
        callBack.execute();
    }
}
