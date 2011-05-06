package nutes.intelimed.model.DAO;

import nutes.intelimed.model.entity.Paciente;
import nutes.intelimed.model.entity.Paciente.Pacientes;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PacienteDao {
	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";
	// Nome da tabela
	public static final String NOME_TABELA = "paciente";

	protected SQLiteDatabase db;
	
	
	
	public PacienteDao()
	{
		
	}
	public PacienteDao(Context ctx) {
		// Abre o banco de dados
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public void save(Paciente paciente) {
		
		inserir(paciente);
	}
	
	public long inserir(Paciente paciente) {
		
		ContentValues values = new ContentValues();
		
		values.put(Pacientes.NOME, paciente.nome);
		values.put(Pacientes.DTNASCIMENTO, paciente.datanascimento);
		
		long id = db.insert(NOME_TABELA, "", values);
		Log.i("jamilson", "Paciente inserido com sucesso!!!");
		return id;
	}
	 /**
		 * Fecha o banco
		 */
		public void fechar() {
			// fecha o banco de dados
			if (db != null) {
				db.close();
			}
		}
}
