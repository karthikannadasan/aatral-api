package com.autolib.helpdesk.Returnable.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autolib.helpdesk.Returnable.entity.Returnable;
import com.autolib.helpdesk.Returnable.entity.ReturnableRequest;
import com.autolib.helpdesk.Returnable.entity.ReturnableSearchRequest;
import com.autolib.helpdesk.Returnable.repository.ReturnableProductRepository;
import com.autolib.helpdesk.Returnable.repository.ReturnableRepository;
import com.autolib.helpdesk.common.Util;

@Repository
public class ReturnableDAOImpl implements ReturnableDAO {

	@Autowired
	ReturnableRepository returnableRepo;

	@Autowired
	ReturnableProductRepository returnableProductRepo;

	@Autowired
	EntityManager entityManager;

	@Transactional
	@Override
	public Map<String, Object> saveReturnable(ReturnableRequest request) {

		Map<String, Object> resp = new HashMap<>();
		try {

			request.setReturnable(returnableRepo.save(request.getReturnable()));

			request.getReturnableProducts().forEach(rp -> {
				rp.setReturnableId(request.getReturnable().getId());
			});

			returnableProductRepo.saveAll(request.getReturnableProducts());
			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

	@Override
	public Map<String, Object> getReturnable(int returnableId) {

		Map<String, Object> resp = new HashMap<>();
		try {

			returnableRepo.findById(returnableId);

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

	@Override
	public Map<String, Object> deleteReturnable(ReturnableRequest request) {

		Map<String, Object> resp = new HashMap<>();
		try {

			returnableRepo.delete(request.getReturnable());

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

	@Override
	public Map<String, Object> searchReturnable(ReturnableSearchRequest request) {
		Map<String, Object> resp = new HashMap<>();

		try {
			String filterQuery = "";

			Query query = entityManager.createQuery("select r from Returnable r where 2 > 1 " + filterQuery,
					Returnable.class);
			resp.put("Returnables", query.getResultList());

			resp.putAll(Util.SuccessResponse());
		} catch (Exception ex) {
			resp.putAll(Util.FailedResponse(ex.getMessage()));
			ex.printStackTrace();
		}
		return resp;
	}

}
