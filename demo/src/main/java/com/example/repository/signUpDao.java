package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.shopInfo;

import jakarta.transaction.Transactional;

@Repository
public interface signUpDao extends JpaRepository<shopInfo, Integer>{

	List<shopInfo> findByEmail(String account);
	
	@Modifying
	@Transactional

	@Query(value = "insert into shop_info(shopName, password,name,phone,email) values(:shopName,:password,:name,:phone,:email)",nativeQuery = true)
	int create(@Param("shopName") String shopName, @Param("password") String password, @Param("name") String name, @Param("phone") String phone, @Param("email") String email);

}
