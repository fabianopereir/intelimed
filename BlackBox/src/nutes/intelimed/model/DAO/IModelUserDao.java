package nutes.intelimed.model.DAO;


import nutes.intelimed.model.entity.User;


/**
 * Interface de usuário
 * @author Jamilson Batista (jamilsonbatista@gmail.com)
 * @author Dyego Carlos (dyego12345@gmail.com)
 * 
 */
public interface IModelUserDao{
	public abstract User login(User user);
	public abstract void fechar();
}