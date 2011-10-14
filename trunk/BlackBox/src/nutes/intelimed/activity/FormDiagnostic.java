package nutes.intelimed.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nutes.intelimed.R;
import nutes.intelimed.controller.diagnostic.DiagnosticController;
import nutes.intelimed.controller.diagnostic.IDiagnostic;
import nutes.intelimed.model.diagnostic.AnswerOption;
import nutes.intelimed.model.diagnostic.StructureQuestionnaire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * Classe respons�vel por montar o diagn�stico na tela e valida��o das op��es de respostas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class FormDiagnostic extends Activity implements OnCheckedChangeListener {

        public static IDiagnostic diagnostic;
        
        private Button validar, logout;
        private String[] arrQuest;
        private String[] arrNO;
        private JSONArray arrJason;
        private JSONObject treeObj;
        private LinearLayout linerLayout;

        
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.questionnaire_asma);

               // dao = (IModelStructureQuestionnaireDao) new StructureQuestionnaireDao(this);
                diagnostic = (IDiagnostic) new DiagnosticController(getBaseContext());
                montarQuest();
                
                validar = (Button) findViewById(R.bt.btOkDiagnostic);
                validar.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                                validar();
                        }
                });
                
                logout = (Button) findViewById(R.bt.btLogoff);
                
                /*back = (Button) findViewById(R.bt.btBack);
        		
                back.setOnClickListener(new OnClickListener() {
        			
        			
        			public void onClick(View v) {
        				treeQ.fechar();
            	        dao.fechar();
        				startActivity(new Intent(getBaseContext(), Menu.class));
        				//	finish();
        				
        			}
        		});*/
                logout.setOnClickListener(new OnClickListener() {
        			public void onClick(View v) {
        				startActivity(new Intent(getBaseContext(), Login.class));
        			}
        		});
        };
        
        
        
        
        /**
         * M�todo respons�vel pela valida��o das respostas e passagem para ResultDiagnostic, 
         * chamado quando o bot�o "OK" � clicado
         * @return void
         */
        public void validar() {
                arrJason = new JSONArray();
                treeObj = new JSONObject();
                int cont = 1;
                diagnostic = (IDiagnostic) new DiagnosticController(getBaseContext());
                for (int i = 0; i < arrQuest.length; i++) {
                        try {
                                treeObj.put("Q" + cont, arrQuest[i]);
                        } catch (JSONException e) {
                                e.printStackTrace();
                        }
                        arrJason.put(arrQuest[i]);
                        cont++;
                }

                List<String> stringListWithouNull = new ArrayList<String>();
                List<String> stringListWithouNullNO = new ArrayList<String>();

                for(int i=0;i<arrQuest.length; i++) { 
                    if(arrQuest[i] != null){
                        stringListWithouNull.add(arrQuest[i]);
                    }
                }
                
                for(int i=0;i<arrNO.length; i++) { 
                    if(arrNO[i] != null){
                        stringListWithouNullNO.add(arrQuest[i]);
                    }
                }
                
                if(stringListWithouNull.size() == stringListWithouNullNO.size()){
                        Intent it = new Intent(getBaseContext(), ResultDiagnostic.class);
                        it.putExtra("answer",arrQuest);
                        it.putExtra("no",arrNO);
                        it.putExtra("diagnostic",diagnostic.controlTree(arrQuest, arrJason, treeObj,arrNO));
                        startActivity(it);
                }else{
                        Toast.makeText(FormDiagnostic.this, "Por favor responda todas as perguntas.", Toast.LENGTH_SHORT).show();

                }
        }

        /**
         * O m�todo � chamado ao clicar em uma resposta, armazenando o valor em array
         * @param group - inst�ncia do RadioGroup
         * @param checkedId - �ndice da resposta selecionada
         * @return void
         */
        public void onCheckedChanged(RadioGroup group, int checkedId) {
                arrQuest[Integer.parseInt(group.getTag().toString())] = Integer.toString(checkedId);
        }

        /**
         * M�todo respons�vel por montar as quest�es do question�rio dinamicamente
         * @return void
         */
        public void montarQuest() {

                ArrayList<StructureQuestionnaire> arrayQuestion = (ArrayList<StructureQuestionnaire>) diagnostic.listarEstruturaQuestionario();
                diagnostic = (IDiagnostic) new DiagnosticController(getBaseContext());
                linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
                ArrayList<AnswerOption> questionOption = new ArrayList<AnswerOption>();

                int aux;
                int aux2 = 0;
                StructureQuestionnaire vAux;
                AnswerOption option;
                arrNO = new String[arrayQuestion.size()];
                arrQuest = new String[arrayQuestion.size()];
                for (int i = 0; i < arrayQuestion.size(); i++) {
                        aux = i;
                        vAux = arrayQuestion.get(aux);
                        linerLayout.addView(createQuestionLabel(arrayQuestion.get(i).getDescricao_no(), arrayQuestion.get(i).getIdno(), this));

                        while (arrayQuestion.get(i).getIdno() == vAux.getIdno() && aux < arrayQuestion.size()) {
                                option = new AnswerOption();
                                option.setCodeResposta(arrayQuestion.get(aux).getIdresposta());
                                option.setIdNo(arrayQuestion.get(aux).getFk_idno());
                                option.setResposta(arrayQuestion.get(aux).getDescricao_resposta());
                                questionOption.add(option);
                                aux++;
                                if (aux < arrayQuestion.size()) {
                                        vAux = arrayQuestion.get(aux);
                                }
                        }
                        aux2++;
                        linerLayout.addView(createQuestionOption(questionOption,aux2, this));
                        arrNO[aux2] = Integer.toString(arrayQuestion.get(aux - 1).getFk_idno());
                        questionOption.clear();
                        i = aux - 1;
                }
        }
        
        public int convertDpToPx(int dp) {
        	final float scale = getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }
        
        /**
         * Monta uma quest�o espec�fica
         * @param pergunta - pergunta que se deseja imprimir na tela
         * @param idno - identificador da pergunta
         * @param formDiagnostic - tela de question�rio
         * @return TextView com uma quest�o espec�fica
         */
        public TextView createQuestionLabel(String pergunta, long idno,FormDiagnostic formDiagnostic) {
        	TextView perguntas = null;
            perguntas = new TextView(formDiagnostic);
            perguntas.setId((int) idno);
            perguntas.setText(pergunta);
            perguntas.setTextColor(Color.WHITE);
            perguntas.setShadowLayer(1, 1, 1, Color.DKGRAY);
            perguntas.setPadding(0, convertDpToPx(15), 0, convertDpToPx(5));

            return perguntas;
        }
        
        /**
         * Monta as op��es de respostas de uma quest�o espec�fica
         * @param questionOption - array de respostas
         * @param radioId - identificador da pergunta
         * @param idno - identificador da pergunta
         * @param formDiagnostic - tela de question�rio
         * @return View com radio group de um quest�o espec�fica
         */
        public View createQuestionOption(ArrayList<AnswerOption> questionOption,int radioId, FormDiagnostic formDiagnostic) {

                RadioGroup radio_group = new RadioGroup(formDiagnostic);
                radio_group.setTag(radioId);
                RadioButton radio_button;
                Iterator<AnswerOption> option = questionOption.iterator();
                
                while (option.hasNext()) {
                        AnswerOption nextOption = option.next();
                        radio_button = new RadioButton(formDiagnostic);
                        radio_button.setId(nextOption.getCodeResposta());
                        radio_button.setTag(nextOption.getIdNo());
                        radio_button.setText(nextOption.getResposta());
                        radio_button.setTextColor(Color.BLACK);
                        radio_group.setBackgroundResource(R.drawable.corner);
                        radio_group.setFocusableInTouchMode(true);
                        radio_group.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
                        radio_group.addView(radio_button);
                }
                radio_group.setOnCheckedChangeListener(formDiagnostic);

                return radio_group;
        }

        /**
    	 * 
    	 * Implementa��o para bot�o voltar de Activity
    	 * @param Indentifica��o de onclick
    	 * @return value boolean
    	 */
    	@Override
    	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
    	        finish();
    	    	startActivity(new Intent(getBaseContext(), Menu.class));
    	        return true;
    	    }
    	    return super.onKeyDown(keyCode, event);
    	}
        
        @Override
        protected void onPause() {
	        super.onPause();
	        setResult(RESULT_CANCELED);
	        finish();

        }
       
}