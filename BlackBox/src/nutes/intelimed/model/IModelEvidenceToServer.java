package nutes.intelimed.model;


import java.util.ArrayList;

import nutes.intelimed.model.entity.EvidenceToServer;

public interface IModelEvidenceToServer {
	public abstract ArrayList<EvidenceToServer> searchEvidenceToServer();
	public abstract void fechar();
}
