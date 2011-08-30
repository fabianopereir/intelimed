package nutes.intelimed;


import android.app.Application;
import android.content.Context;

public class InteliMEDContext extends Application{
	
    private static InteliMEDContext instance = null;

    private InteliMEDContext(){
        instance = this;
    }

    public static Context getInstance(){
        if (null == instance){
            instance = new InteliMEDContext();
        }

        return instance;
    }
}