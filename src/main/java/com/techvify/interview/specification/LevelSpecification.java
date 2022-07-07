package com.techvify.interview.specification;

import com.techvify.interview.entity.Level;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class LevelSpecification {
	@SuppressWarnings("deprecation")
	public static Specification<Level> buildWhere(String search) {
		
		Specification<Level> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			
		search = search.trim();
		
		CustomSpecification code = new CustomSpecification("code", search);
		CustomSpecification name = new CustomSpecification("name", search);

		where = Specification.where(code).or(name);
		}	
	
		return where;
	}
}
@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Level> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	public CustomSpecification(String string, String search) {
		this.field = string;
		this.value = search;
	}
	

	@Override
	public Predicate toPredicate(
			Root<Level> root, 
			CriteriaQuery<?> query, 
			CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("code")) {
			return criteriaBuilder.like(root.get("code"), "%" + value.toString() + "%");
		}
		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		return null;
	}
}
