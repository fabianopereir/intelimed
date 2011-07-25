package nutes.intelimed.controller.deusdara;

import java.util.ArrayList;

import nutes.intelimed.controller.activity.DiagnosticForm;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela engine inteligente
 */
public class BlackBox {
	
	public void TreeQuestionnaire()
	{
		
	}
	
	/**
	 * 
	 * @param arrQuest, arrayJason, treeObj
	 * @return array de Strings com questões e suas respectivas respostas
	 */
	public String[] controlTree(String[] arrQuest, JSONArray arrayJason, JSONObject treeObj) {
		// TODO Auto-generated method stub
		String[] res = new String[4];
		for (int i=0;i<4;i++){
			
			System.out.println("arrQuest[i] = "+arrQuest[i]);
			
			if(arrQuest[i]=="1"){
				arrQuest[i]="a";
			}else if(arrQuest[i]=="2"){
				arrQuest[i]="b";
			}else if(arrQuest[i]=="3"){
				arrQuest[i]="c";
			}else if(arrQuest[i]=="4"){
				arrQuest[i]="d";
			}
			
			
			res[i] = "Questão "+ (i+1) +": Resposta "+ arrQuest[i]+". ";
		}
		
		System.out.println(res);
		
		return res;
	}
	
	public TextView createTypeMetrics(String pergunta, String tipo, ArrayList<String> questionOption, DiagnosticForm diagnosticForm)
	{
		TextView perguntas = null;
		perguntas = new TextView(diagnosticForm);
    	perguntas.setText(pergunta);
    	
    	return perguntas;
	}

	public View createTypeMetricsG(String question, String tipo,ArrayList<String> questionOption, DiagnosticForm diagnosticForm) {
		RadioGroup radio_group = new RadioGroup ( diagnosticForm );
        RadioButton radio_button_1 = new RadioButton ( diagnosticForm );
        radio_button_1.setId ( 1 );
        radio_button_1.setText("Sim");
        RadioButton radio_button_2 = new RadioButton ( diagnosticForm );
        radio_button_2.setId ( 2 );
        radio_button_2.setText("Não");
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams ( 200, 100 );
        radio_group.addView ( radio_button_1, 0, params );
        radio_group.addView ( radio_button_2, 1, params );
        radio_group.check ( 2 );
        radio_group.setOnCheckedChangeListener ( diagnosticForm );
        
		return radio_group;
	}
	
}
