package nutes.intelimed.model.DAO;

import java.util.ArrayList;
import java.util.List;

import nutes.intelimed.model.InterfaceModelPaciente;
import nutes.intelimed.model.entity.Paciente;
import nutes.intelimed.model.entity.ReportSuspeita;
import nutes.intelimed.model.entity.Paciente.Pacientes;
import nutes.intelimed.model.entity.ReportSuspeita.Suspeita;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import android.util.Log;

public class PacienteDao implements InterfaceModelPaciente {

	private static final String CATEGORIA = "nutes";
	// Nome do banco
	private static final String NOME_BANCO = "inteliMobile";
	// Nome da tabela
	public static final String NOME_TABELA = "paciente";

	protected SQLiteDatabase db;

	public PacienteDao() {

	}

	public PacienteDao(Context ctx) {
		// Abre o banco de dados
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

	public void save(Paciente paciente) {
		long id = paciente.id;
		if (id != 0) {
			atualizar(paciente);
		} else {
			inserir(paciente);
		}
	}

	public long inserir(Paciente paciente) {

		ContentValues values = new ContentValues();

		values.put(Pacientes.NOME, paciente.nome);
		values.put(Pacientes.DTNASCIMENTO, paciente.datanascimento);
		long id = db.insert(NOME_TABELA, "", values);
		Log.i("jamilson", "Paciente inserido com sucesso!!!");
		return id;
	}

	@Override
	public int atualizar(Paciente paciente) {
		Log.i("jamilson", "dentro do método atualizar");
		ContentValues values = new ContentValues();
		values.put(Pacientes.NOME, paciente.nome);
		values.put(Pacientes.DTNASCIMENTO, paciente.datanascimento);
		
		String _id = String.valueOf(paciente.id);
		String where = Pacientes._ID + "=?";
		String[] whereArgs = new String[] { _id };
		
		
		Log.i("jamilson", "ID: "+Pacientes._ID);
		Log.i("jamilson", "where: "+where);
		Log.i("jamilson", "whereArgs: "+whereArgs);
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros");

		return count;
	}

	@Override
	public Paciente buscarPaciente(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paciente buscarPacientePorNome(String nome) {
		Paciente paciente = null;

		try {
			Log.i("jamilson", "dentro do método buscarPacientePorNome");
			Cursor c = db.query(NOME_TABELA, Paciente.colunas, Pacientes.NOME
					+ "='" + nome + "'", null, null, null, null);

			// Se encontrou...
			if (c.moveToNext()) {

				paciente = new Paciente();

				// utiliza os métodos getLong(), getString(), getInt(), etc para
				// recuperar os valores
				paciente.id = c.getLong(0);
				paciente.nome = c.getString(1);
				paciente.datanascimento = c.getString(2);
			}
			// c.close();
		} catch (SQLException e) {
			Log.e(CATEGORIA,
					"Erro ao buscar a paciente pelo nome: " + e.toString());
			return null;
		}

		return paciente;
	}

	@Override
	public int deletar(long id) {
		String where = Pacientes._ID + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");

		return count;
	}

	public Cursor getCursor() {
		try {
			Log.i("jamilson", "dentro do metodo getCursor");
			Cursor cursor = db.query(NOME_TABELA, Paciente.colunas, null, null, null,
					null, null, null);
			return cursor;
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar as pacientes: " + e.toString());
			return null;
		}
	}

	@Override
	public List<Paciente> listarPacientes() {
		Log.i("jamilson", "dentro do metodo listarPacientes");
		Cursor c = getCursor();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		Log.i("jamilson", "abaixo metodo listarPacientes");
		Log.i("jamilson", "dentro do loop do metodo listarPacientes");
		// Recupera os índices das colunas
		int idxId = c.getColumnIndex(Pacientes._ID);
		int idxNome = c.getColumnIndex(Pacientes.NOME);
		int idxDatanascimento = c.getColumnIndex(Pacientes.DTNASCIMENTO);

		if (c.moveToFirst()) {
			// Loop até o final
			do {
				Paciente paciente = new Paciente();
				pacientes.add(paciente);

				paciente.id = c.getLong(idxId);
				paciente.nome = c.getString(idxNome);
				Log.i("jamilson", c.getString(idxNome));
				paciente.datanascimento = c.getString(idxDatanascimento);
				Log.i("jamilson", c.getString(idxDatanascimento));
			} while (c.moveToNext());
		}
		return pacientes;
	}

	@Override
	public void salvar(Paciente paciente) {
		Log.i("jamilson", "chamou salvar");
		ContentValues values = new ContentValues();

		values.put(Pacientes.NOME, paciente.nome);
		values.put(Pacientes.DTNASCIMENTO, paciente.datanascimento);

		long id = db.insert(NOME_TABELA, "", values);
		Log.i("jamilson", "Paciente inserido com sucesso!!!");
		// return id;
	}

	/**
	 * Busca um paciente utilizando as configurações definidas no
	 * SQLiteQueryBuilder Utilizado pelo Content Provider de paciente
	 */
	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy) {
		Cursor c = queryBuilder.query(this.db, projection, selection,
				selectionArgs, groupBy, having, orderBy);
		return c;
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
