package nutes.intelimed.model.DAO;


import java.util.ArrayList;

import nutes.intelimed.model.entity.EvidenceServer;

public interface IModelEvidenceServerDao {
	public abstract ArrayList<EvidenceServer> searchEvidenceToServer();
	public abstract void fechar();
}
