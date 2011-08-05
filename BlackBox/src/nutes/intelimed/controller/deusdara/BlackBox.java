package nutes.intelimed.controller.deusdara;

import java.util.ArrayList;
import java.util.Iterator;

import nutes.intelimed.controller.activity.DiagnosticForm;
import nutes.intelimed.util.AnswerOption;

import org.json.JSONArray;
import org.json.JSONObject;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * 
 * @author Jamilson Batista e Dyego Carlos
 * @Description classe respons�vel pela engine inteligente
 */
public class BlackBox {
	
	public BlackBox(){}
	
	/**
	 * @Description M�todo que processa resultado
	 * @param arrQuest - array de respostas
	 * @param arrayJason - array de respostas em JSON
	 * @param treeObj - objeto JSON com respostas
	 * @return array de Strings com quest�es e suas respectivas respostas marcadas
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
				res[i] = "Quest�o "+ (aux) +": Resposta "+ arrQuest[i]+". ";
			}
		}
		
		return res;
	}
	
	/**
	 * @Description Monta uma quest�o espec�fica
	 * @param pergunta - pergunta que se deseja imprimir na tela
	 * @param idno 
	 * @param diagnosticForm - tela de question�rio
	 * @return TextView com uma quest�o espec�fica
	 */
	public TextView createTypeMetrics(String pergunta, long idno, DiagnosticForm diagnosticForm)
	{
		TextView perguntas = null;
		perguntas = new TextView(diagnosticForm);
		perguntas.setId((int)idno);
    	perguntas.setText(pergunta);
    	
    	return perguntas;
	}

	/**
	 * @Description Monta as op��es de respostas de uma quest�o espec�fica
	 * @param questionOption - array de respostas
	 * @param radioId - identificador da pergunta
	 * @param diagnosticForm - tela de question�rio
	 * @return View com radio group de um quest�o espec�fica
	 */
	public View createTypeMetricsG(ArrayList<AnswerOption> questionOption, int radioId, DiagnosticForm diagnosticForm) {
		
		RadioGroup radio_group = new RadioGroup (diagnosticForm);
		radio_group.setTag(radioId);
		RadioButton radio_button;
		int i = 0;
		Iterator<AnswerOption> option = questionOption.iterator();
		while (option.hasNext()) {
			AnswerOption nextOption = option.next();
			radio_button = new RadioButton (diagnosticForm);
	        radio_button.setId (nextOption.codeResposta);
	        radio_button.setText(nextOption.resposta);
	        radio_group.addView (radio_button);
	        i++;
			
		}
		 /*for(int i = 0; i < questionOption.size(); i++)
		 {
			
			
			radio_button = new RadioButton (diagnosticForm);
	        radio_button.setId (i+1);
	        radio_button.setText(questionOption.get(i));
	        radio_group.addView (radio_button);
		 }*/
		radio_group.setOnCheckedChangeListener (diagnosticForm);
		
		return radio_group;
	}
	
}
