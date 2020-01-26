package com.unimib.emac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unimib.common.Observer;
import com.unimib.smarthome.entity.Entity;
import com.unimib.smarthome.entity.EntityManager;
import com.unimib.smarthome.request.Request;


public class Emac implements Observer {

	static Emac instance;
	
	
	private Map<Integer, List<Request>> idToRequests = new HashMap<>();
	
	//Singleton
	public static Emac getInstance() {
		if(instance == null) {				
			instance = new Emac();
			}
		return instance;
	}
	
	public void registerAutomation(Request r) {
		
		for (int i = 0; i < r.getCondition().length; i++) {
			//id di un'entità tra le condition di r
			int entityId = r.getCondition()[i].getEntityID();
			//lista di richieste già salvate relative a quell'entità
			List<Request> requests = idToRequests.get(entityId);
			//aggiungo r alla lista
			requests.add(r);
			//aggiungo la lista aggiornata alla mappa
			idToRequests.put(entityId, requests);
		}
	}
	
	

	
	public void filter(Entity entity) {
		//tutte le richieste relative ad entity
		List<Request> requests = idToRequests.get(entity.getId());
		List<Request> validRequests = new ArrayList<>();
		//filtra le sole valide
		for (Request r : requests) {
			boolean verified = true;
			for (int i = 0; i < r.getCondition().length; i++) {
				if (!r.getCondition()[i].getState().equals(EntityManager.getInstance().getEntityState(entity.getId()))) {
					verified = false;
				}
			}
			if (verified) {
				validRequests.add(r);
			}
		}
	}

	@Override
	public void update(Integer subjectId, String subjectState) {
		// TODO Auto-generated method stub
		Observer.super.update(subjectId, subjectState);
		
	}
	
	

	
}
