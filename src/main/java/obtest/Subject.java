package obtest;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ObTest> observer = new ArrayList<ObTest>();

    public void update(){
        for (ObTest o : observer) {
            o.update();
        }
    }

    public void remove(ObTest object){
        if(observer.equals(object)){
            observer.remove(object);
        }
    }

    public void attach(ObTest object){
        if(observer.equals(object)){
            return;
        }
        observer.add(object);
    }
}
