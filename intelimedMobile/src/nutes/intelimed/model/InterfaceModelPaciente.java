package nutes.intelimed.model;

import java.util.List;

import nutes.intelimed.model.entity.Paciente;

public interface InterfaceModelPaciente {
	public abstract long inserir(Paciente paciente);
	public abstract int atualizar(Paciente paciente);
	public abstract int deletar(long id);
	public abstract Paciente buscarPaciente(long id);
	public abstract Paciente buscarPacientePorNome(String nome);
	public abstract List<Paciente> listarPacientes();
	public abstract void fechar();
	public abstract void salvar(Paciente paciente);
}
