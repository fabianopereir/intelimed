package nutes.intelimed.communication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nutes.intelimed.controller.activity.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class NKHttpClient extends Activity{
       
        /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
       
       final EditText txtUrl = (EditText)findViewById(R.id.url);
       final Button btnFetch = (Button)findViewById(R.id.button);
       final TextView txtResult = (TextView)findViewById(R.id.content);
   
       btnFetch.setOnClickListener(new Button.OnClickListener(){
           public void onClick(View v){
               getRequest(txtResult,txtUrl);
           }
       });
   }
   
   public void getRequest(TextView txtResult, EditText txtUrl){
       String url = txtUrl.getText().toString();
       HttpClient client = new DefaultHttpClient();
       HttpGet request = new HttpGet(url);
       try{
           HttpResponse response = client.execute(request);
           txtResult.setText(HttpHelper.request(response));
       }catch(Exception ex){
           txtResult.setText("Failed!");
       }
   }

}