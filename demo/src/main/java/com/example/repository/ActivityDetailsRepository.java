package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Activitydetails;
import com.example.entity.Activitys;
import com.example.entity.Activitydetails.ActivityDetailsId;


public interface ActivityDetailsRepository extends JpaRepository<Activitydetails, ActivityDetailsId> {

	//List<Activitys> findByActivityId(String activityId);
	void deleteByProductsIdAndActivitysId(String productsId, String activitysId);
	
}
