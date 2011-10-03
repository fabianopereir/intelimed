package nutes.intelimed.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nutes.intelimed.R;
import nutes.intelimed.controller.BlackBox;
import nutes.intelimed.controller.IBlackBox;
import nutes.intelimed.model.BaseScript;
import nutes.intelimed.model.DAO.IModelStructureQuestionnaireDao;
import nutes.intelimed.model.entity.StructureQuestionnaire;
import nutes.intelimed.util.AnswerOption;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * Classe responsável por montar o diagnóstico na tela e validação das opções de respostas
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public class FormDiagnostic extends Activity implements OnCheckedChangeListener {

        public static IModelStructureQuestionnaireDao dao;
        public static IBlackBox treeQ;
        
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

                dao = (IModelStructureQuestionnaireDao) new BaseScript(this);
                
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
        				treeQ.fechar();
            	        dao.fechar();
        				startActivity(new Intent(getBaseContext(), Login.class));
        				//	finish();
        				
        			}
        		});
        };
        
        
        
        
        /**
         * Método responsável pela validação das respostas e passagem para ResultDiagnostic, 
         * chamado quando o botão "OK" é clicado
         * @return void
         */
        public void validar() {
                arrJason = new JSONArray();
                treeObj = new JSONObject();
                int cont = 1;
                treeQ = (IBlackBox) new BlackBox(getBaseContext());
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
                        it.putExtra("diagnostic",treeQ.controlTree(arrQuest, arrJason, treeObj,arrNO));
                        startActivity(it);
                }else{
                        Toast.makeText(FormDiagnostic.this, "Por favor responda todas as perguntas.", Toast.LENGTH_SHORT).show();

                }
                treeQ.fechar();
                dao.fechar();
        }

        /**
         * O método é chamado ao clicar em uma resposta, armazenando o valor em array
         * @param group - instância do RadioGroup
         * @param checkedId - índice da resposta selecionada
         * @return void
         */
        public void onCheckedChanged(RadioGroup group, int checkedId) {
                arrQuest[Integer.parseInt(group.getTag().toString())] = Integer.toString(checkedId);

        }

        /**
         * Método responsável por montar as questões do questionário dinamicamente
         * @return void
         */
        public void montarQuest() {

                ArrayList<StructureQuestionnaire> arrayQuestion = (ArrayList<StructureQuestionnaire>) dao.listarEstruturaQuestionario();
                treeQ = (IBlackBox) new BlackBox(getBaseContext());
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
                                option.codeResposta = arrayQuestion.get(aux).getIdresposta();
                                option.fk_idno = arrayQuestion.get(aux).getFk_idno();
                                option.resposta = arrayQuestion.get(aux).getDescricao_resposta();
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
                treeQ.fechar();
                dao.fechar();
        }
        
        public int convertDpToPx(int dp) {
        	final float scale = getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }
        
        /**
         * Monta uma questão específica
         * @param pergunta - pergunta que se deseja imprimir na tela
         * @param idno - identificador da pergunta
         * @param formDiagnostic - tela de questionário
         * @return TextView com uma questão específica
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
         * Monta as opções de respostas de uma questão específica
         * @param questionOption - array de respostas
         * @param radioId - identificador da pergunta
         * @param idno - identificador da pergunta
         * @param formDiagnostic - tela de questionário
         * @return View com radio group de um questão específica
         */
        public View createQuestionOption(ArrayList<AnswerOption> questionOption,int radioId, FormDiagnostic formDiagnostic) {

                RadioGroup radio_group = new RadioGroup(formDiagnostic);
                radio_group.setTag(radioId);
                RadioButton radio_button;
                Iterator<AnswerOption> option = questionOption.iterator();
                
                while (option.hasNext()) {
                        AnswerOption nextOption = option.next();
                        radio_button = new RadioButton(formDiagnostic);
                        radio_button.setId(nextOption.codeResposta);
                        radio_button.setTag(nextOption.fk_idno);
                        radio_button.setText(nextOption.resposta);
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
    	 * Implementação para botão voltar de Activity
    	 * @param Indentificação de onclick
    	 * @return value boolean
    	 */
    	@Override
    	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
    	        finish();
    	        treeQ.fechar();
    	        dao.fechar();
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
        
        @Override
    	protected void onDestroy() {
    		super.onDestroy();
    		// Fecha o banco
    		treeQ.fechar();
    		dao.fechar();
    	}
}
