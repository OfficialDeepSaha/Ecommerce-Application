package com.ECommerce.Project.developBy.DeepSaha;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;





public class OrderSpecification {

	private OrderSpecification() {
		
	}

	@SuppressWarnings("serial")
	public static Specification<Order> filterBy(String search) {			
		return new Specification<Order>() {
	
			
			 @Override
	            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	                List<Predicate> predicates = new ArrayList<>();                
	                query.distinct(true);                
			
			
	                if(search!=null && !search.isEmpty()) {
	                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("id"), "%"+search+"%")));
	                }
	                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}
			 
		};			 
			 
			 
		}
}