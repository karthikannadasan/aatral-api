package com.autolib.helpdesk.Returnable.service;

import java.util.Map;

import com.autolib.helpdesk.Returnable.entity.ReturnableRequest;
import com.autolib.helpdesk.Returnable.entity.ReturnableSearchRequest;

public interface ReturnableService {

	Map<String, Object> saveReturnable(ReturnableRequest request);

	Map<String, Object> getReturnable(int returnableId);

	Map<String, Object> deleteReturnable(ReturnableRequest request);

	Map<String, Object> searchReturnable(ReturnableSearchRequest request);

}
