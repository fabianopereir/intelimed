package nutes.intelimed.controller.deusdara;

import java.util.ArrayList;
import java.util.Iterator;

import nutes.intelimed.controller.activity.DiagnosticForm;
import nutes.intelimed.model.entity.StructureQuestionnaireTest;

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
		String[] res = new String[arrQuest.length];
		int aux=0;
		for (int i=0;i<arrQuest.length;i++){
			
			System.out.println("arrQuest["+i+"] = "+arrQuest[i]);
			if (arrQuest[i]!=null)
			{
				aux++;
				if(arrQuest[i].equals("1")){
					arrQuest[i]="a";
				}else if(arrQuest[i].equals("2")){
					arrQuest[i]="b";
				}else if(arrQuest[i].equals("3")){
					arrQuest[i]="c";
				}else if(arrQuest[i].equals("4")){
					arrQuest[i]="d";
				}
				res[i] = "Questão "+ (aux) +": Resposta "+ arrQuest[i]+". ";
			}
		}
		
		System.out.println(res);
		
		return res;
	}
	
	public TextView createTypeMetrics(String pergunta, DiagnosticForm diagnosticForm)
	{
		TextView perguntas = null;
		perguntas = new TextView(diagnosticForm);
    	perguntas.setText(pergunta);
    	
    	return perguntas;
	}

	public View createTypeMetricsG(ArrayList<String> questionOption, int radioId, DiagnosticForm diagnosticForm) {
		
		RadioGroup radio_group = new RadioGroup (diagnosticForm);
		radio_group.setTag(radioId);
		RadioButton radio_button;
		
		 for(int i = 0; i < questionOption.size(); i++)
		 {
			radio_button = new RadioButton (diagnosticForm);
	        radio_button.setId (i+1);
	        radio_button.setText(questionOption.get(i));
	        
	        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams (200, 50);
	        radio_group.addView (radio_button, 0, params);
		 }
		radio_group.setOnCheckedChangeListener (diagnosticForm);
		
		return radio_group;
	}
	
}
