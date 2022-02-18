package com.autolib.helpdesk.LeadManagement.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autolib.helpdesk.LeadManagement.model.Lead;

public interface LeadRepository extends JpaRepository<Lead, Integer> {

	Lead findById(int leadId);

	@Query(value = "select DISTINCT(l.company) from Lead l")
	List<String> findDistinctCompanies();

	@Query(value = "select DISTINCT(l.products) from Lead l")
	List<String> findDistinctProducts();

	@Query(value = "select DISTINCT(l.industryType) from Lead l")
	List<String> findDistinctIndustryType();

	@Query(value = "select DISTINCT(l.leadSource) from Lead l")
	List<String> findDistinctLeadSources();

	@Query(value = "select DISTINCT(l.status) from Lead l")
	List<String> findDistinctLeadStatus();

	List<Lead> findByStatusIn(Set<String> status);

	List<Lead> findByStatus(String status);

}
