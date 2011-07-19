package nutes.intelimed.model;

import java.util.List;

import android.content.Entity;

import nutes.intelimed.model.entity.Question;

public interface InterfaceModelQuestion {
	public abstract List<Question> listarPerguntas();
	public abstract void fechar();
}
