package nutes.intelimed.model;


import java.util.ArrayList;

import nutes.intelimed.model.entity.EvidenceToServer;

public interface IModelEvidenceToServer {
	public abstract ArrayList<Object> searchEvidenceToServer();
	public abstract void fechar();
}
