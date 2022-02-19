package com.autolib.helpdesk.Returnable.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autolib.helpdesk.Returnable.dao.ReturnableDAO;
import com.autolib.helpdesk.Returnable.entity.ReturnableRequest;
import com.autolib.helpdesk.Returnable.entity.ReturnableSearchRequest;

@Service
public class ReturnableServiceImpl implements ReturnableService {

	@Autowired
	ReturnableDAO returnableDAO;

	@Override
	public Map<String, Object> saveReturnable(ReturnableRequest request) {
		// TODO Auto-generated method stub
		return returnableDAO.saveReturnable(request);
	}

	@Override
	public Map<String, Object> getReturnable(int returnableId) {
		// TODO Auto-generated method stub
		return returnableDAO.getReturnable(returnableId);
	}

	@Override
	public Map<String, Object> deleteReturnable(ReturnableRequest request) {
		// TODO Auto-generated method stub
		return returnableDAO.deleteReturnable(request);
	}

	@Override
	public Map<String, Object> searchReturnable(ReturnableSearchRequest request) {
		// TODO Auto-generated method stub
		return returnableDAO.searchReturnable(request);
	}
}
