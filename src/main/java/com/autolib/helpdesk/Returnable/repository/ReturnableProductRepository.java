package com.autolib.helpdesk.Returnable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autolib.helpdesk.Returnable.entity.ReturnableProduct;

public interface ReturnableProductRepository extends JpaRepository<ReturnableProduct, Integer> {

}
