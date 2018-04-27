package br.com.itego.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("Fechou em: " + event.getPhaseId());
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Fase: " + event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
