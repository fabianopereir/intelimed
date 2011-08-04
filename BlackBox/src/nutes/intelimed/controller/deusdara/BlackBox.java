package nutes.intelimed.controller.deusdara;

import java.util.ArrayList;

import nutes.intelimed.controller.activity.DiagnosticForm;

import org.json.JSONArray;
import org.json.JSONObject;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe responsável pela engine inteligente
 */
public class BlackBox {
	
	public void TreeQuestionnaire()
	{
		
	}
	
	/**
	 * @Description Método que processa resultado
	 * @param arrQuest - array de respostas
	 * @param arrayJason - array de respostas em JSON
	 * @param treeObj - objeto JSON com respostas
	 * @return array de Strings com questões e suas respectivas respostas marcadas
	 */
	public String[] controlTree(String[] arrQuest, JSONArray arrayJason, JSONObject treeObj) {
		String[] res = new String[arrQuest.length];
		int aux=0;
		for (int i=0;i<arrQuest.length;i++){
			
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
		
		return res;
	}
	
	/**
	 * @Description Monta uma questão específica
	 * @param pergunta - pergunta que se deseja imprimir na tela
	 * @param diagnosticForm - tela de questionário
	 * @return TextView com uma questão específica
	 */
	public TextView createTypeMetrics(String pergunta, DiagnosticForm diagnosticForm)
	{
		TextView perguntas = null;
		perguntas = new TextView(diagnosticForm);
    	perguntas.setText(pergunta);
    	
    	return perguntas;
	}

	/**
	 * @Description Monta as opções de respostas de uma questão específica
	 * @param questionOption - array de respostas
	 * @param radioId - identificador da pergunta
	 * @param diagnosticForm - tela de questionário
	 * @return View com radio group de um questão específica
	 */
	public View createTypeMetricsG(ArrayList<String> questionOption, int radioId, DiagnosticForm diagnosticForm) {
		
		RadioGroup radio_group = new RadioGroup (diagnosticForm);
		radio_group.setTag(radioId);
		RadioButton radio_button;
		
		 for(int i = 0; i < questionOption.size(); i++)
		 {
			radio_button = new RadioButton (diagnosticForm);
	        radio_button.setId (i+1);
	        radio_button.setText(questionOption.get(i));
	        radio_group.addView (radio_button);
		 }
		radio_group.setOnCheckedChangeListener (diagnosticForm);
		
		return radio_group;
	}
	
}
