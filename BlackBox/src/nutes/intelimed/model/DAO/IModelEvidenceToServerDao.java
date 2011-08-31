package nutes.intelimed.model.DAO;


import java.util.ArrayList;

import nutes.intelimed.model.entity.EvidenceToServer;

public interface IModelEvidenceToServerDao {
	public abstract ArrayList<EvidenceToServer> searchEvidenceToServer();
	public abstract void fechar();
}
