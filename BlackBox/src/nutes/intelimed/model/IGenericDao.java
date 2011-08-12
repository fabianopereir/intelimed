package nutes.intelimed.model;

import java.util.List;

public interface IGenericDao <TEntidade,TChave> {
	
	TEntidade save (TEntidade obj);
	TEntidade update (TEntidade obj);
	boolean delete (TEntidade obj);
	List <TEntidade> listAll();
	boolean exists (TChave key);
	TEntidade open (TChave key);
	
}