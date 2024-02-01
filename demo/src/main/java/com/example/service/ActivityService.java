package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.classes.ActivitysDTO;
import com.example.entity.Activitydetails;
import com.example.entity.Activitys;
import com.example.entity.Products;
import com.example.repository.ActivityDetailsRepository;
import com.example.repository.ActivityRepository;
import com.example.repository.ProductsResposity;


@Service
public class ActivityService {
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private ActivityDetailsRepository activityDetailsRepository;
	
	@Autowired
	private ProductsResposity productsResposity;
	
	
	//查全部活動
	public List<Activitys> queryActivitys(String shopId) {
		return activityRepository.findByShopId(shopId);	
	}

	//增加活動
	public String addActivityWithDetails(Activitys activitys, List<Activitydetails> activitydetailsList) {
		activityRepository.save(activitys);
		
        for (Activitydetails activitydetails : activitydetailsList) {

            Products existingProduct = productsResposity.findById(activitydetails.getProducts().getId())
                    .orElseThrow();

        	activitydetails.setActivitys(activitys);
            activitydetails.setProducts(existingProduct);
         
            activityDetailsRepository.save(activitydetails);
        }

        return "Activity and Activitydetails added successfully!";
    }


	//刪除活動
    public void deleteActivitys(String activityId) {
		try {
			List<Activitydetails> activitydetaillist= activityDetailsRepository.findByActivitysId(activityId);
			activityDetailsRepository.deleteAll(activitydetaillist);
			activityRepository.deleteById(activityId);
		
		}catch(Exception e) {
			throw new RuntimeException("Error deleting activity and details2", e);
		}
		
    }

	
	//查一筆活動
	public ActivitysDTO getActivityWithDetails(String activityId) throws NotFoundException {
        Optional<Activitys> activitysOptional = activityRepository.findById(activityId);
        if (activitysOptional.isPresent()) {
            Activitys activitys = activitysOptional.get();
            
            List<Activitydetails> activitydetailsList = activityDetailsRepository.findByActivitysId(activityId);

            ActivitysDTO activitysDTO = new ActivitysDTO();
            activitysDTO.setActivitys(activitys);
            activitysDTO.setActivitydetailsList(activitydetailsList);

            return activitysDTO;
        } else {
            throw new NotFoundException();
        }
    }

	//編輯活動
	public String updateActivityWithDetails(Activitys activitys, List<Activitydetails> activitydetailsList) {
		List<Activitydetails> activitydetail= activityDetailsRepository.findByActivitysId(activitys.getId());
		activityDetailsRepository.deleteAll(activitydetail);
		
		activityRepository.save(activitys);

        for (Activitydetails activitydetails : activitydetailsList) {
        	Products existingProduct = productsResposity.findById(activitydetails.getProducts().getId())
                    .orElseThrow();

        	activitydetails.setActivitys(activitys);
            activitydetails.setProducts(existingProduct);
            
            activityDetailsRepository.save(activitydetails);
        }

        return "Activity and Activitydetails edited successfully!";
    }
	
	
	//查全部產品
	public List<Products> queryProducts(String shopId) {
		return productsResposity.findByShopIdOrderByNameAsc(shopId);	
	}
	
	//依產品id查折扣
	public double queryDiscount(String productId) {
		List<Activitydetails> activitydiscountlist = activityDetailsRepository.findByProductsId(productId);
		double minDiscount = activitydiscountlist.stream()
			    .mapToDouble(Activitydetails::getDiscount)
			    .min()
			    .orElse(1.0); // 如果列表為空，返回預設值，這裡設為 0.0
		return minDiscount;
	}
	
	//查產品折扣
	public List<Object[]> findProductsAndMinDiscountsByShopId(String shopId) {
        return productsResposity.findProductsAndMinDiscountsByShopId(shopId);
    }

	
}

	

