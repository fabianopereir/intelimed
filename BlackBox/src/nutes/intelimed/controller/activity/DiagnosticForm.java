package nutes.intelimed.controller.activity;

import java.util.ArrayList;

import nutes.intelimed.controller.deusdara.BlackBox;
import nutes.intelimed.model.InterfaceModelStructureQuestionnaire;
import nutes.intelimed.model.StructureQuestionnaireScript;
import nutes.intelimed.model.entity.StructureQuestionnaire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author Jamilson Batista and Dyego Carlos
 * @Description classe responsável pela montagem do formulário de diagnóstico
 */
public class DiagnosticForm extends Activity implements OnCheckedChangeListener {
    
    public static InterfaceModelStructureQuestionnaire dao;
    RadioGroup rQuest1, rQuest2, rQuest3, rQuest4;
    
    ArrayList<RadioGroup> arrQuestions = new ArrayList<RadioGroup>();
    Button validar;
    String[] arrQuest = new String[50];
    JSONArray arrayJason;
    ImageButton back, logout;
    BlackBox treeQ;
    JSONObject treeObj;
    LinearLayout linerLayout;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_asma);
        
        dao = (InterfaceModelStructureQuestionnaire) new StructureQuestionnaireScript(this);
        montarQuest();
        
        validar = (Button) findViewById(R.id.ok);
        
        validar.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                arrayJason = new JSONArray();
                treeObj = new JSONObject();
                int cont = 1;
                treeQ = new BlackBox();
                for (int i = 0; i < arrQuest.length; i++) {
                    try {
                        treeObj.put("Q" + cont, arrQuest[i]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayJason.put(arrQuest[i]);
                    cont++;
                }
                System.out.println(arrayJason);
                System.out.println(treeObj);

                Intent it = new Intent(getBaseContext(), DiagnosticResult.class);
                it.putExtra("questionnaireData",treeQ.controlTree(arrQuest, arrayJason, treeObj));
                startActivity(it);
            }

        });
    }
    
    public void onCheckedChanged ( RadioGroup group, int checkedId ) 
        {
            Toast.makeText(DiagnosticForm.this, Integer.toString(checkedId), Toast.LENGTH_SHORT).show();
            
            arrQuest[Integer.parseInt(group.getTag().toString())] = Integer.toString(checkedId);
        }
     
    public void montarQuest()
    {
        ArrayList<StructureQuestionnaire>  arrayQuestion = (ArrayList<StructureQuestionnaire>) dao.listarEstruturaQuestionario();
        
        treeQ = new BlackBox();
        linerLayout = (LinearLayout) findViewById(R.id.LinearLayout02);
        
        ArrayList<String> questionOption = new ArrayList<String>();
         
        int aux;
        int aux2=0;
        StructureQuestionnaire vAux;
         for(int i = 0; i < arrayQuestion.size(); i++)
         {
             aux = i;
             vAux = arrayQuestion.get(aux);
             if (arrayQuestion.get(i).getTipo().equals("radio group"))
                {
                 linerLayout.addView(treeQ.createTypeMetrics(arrayQuestion.get(i).getPergunta(), this));
                 while (arrayQuestion.get(i).getIdestrutura_questionario() == vAux.getIdestrutura_questionario() && aux < arrayQuestion.size())
                    {
                            questionOption.add(arrayQuestion.get(aux).getDescricao());
                            aux++;
                            if (aux<arrayQuestion.size())
                            {
                                vAux = arrayQuestion.get(aux);
                            }    
                    }
                     aux2++;
                     linerLayout.addView(treeQ.createTypeMetricsG(questionOption, aux2, this));
                     questionOption.clear();
                     i = aux-1;
                }
         }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        setResult(RESULT_CANCELED);
        finish();
    }
}