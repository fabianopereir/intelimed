package nutes.intelimed.model;


import nutes.intelimed.model.entity.User;


/**
 * Interface de usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelUser{
	public abstract User login(User user);
	public abstract void fechar();
}