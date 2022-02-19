package com.autolib.helpdesk.Returnable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autolib.helpdesk.Returnable.entity.Returnable;

public interface ReturnableRepository extends JpaRepository<Returnable, Integer> {

}
