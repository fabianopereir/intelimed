package nutes.intelimed.model;


import nutes.intelimed.model.entity.Usuario;

public interface InterfaceModelUsuario{
	public abstract Usuario login(Usuario user);
	public abstract void fechar();
}