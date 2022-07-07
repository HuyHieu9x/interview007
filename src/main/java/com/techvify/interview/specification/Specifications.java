package com.techvify.interview.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.techvify.interview.payLoad.request.Filter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class  Specifications{
    @SuppressWarnings("deprecation")

    public static <E> Specification<E> buildWhere(String search, Filter filter ) {

        Specification<E> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = search.trim();

            CustomSpecificationQuestion name = new CustomSpecificationQuestion("name", search);

            where = Specification.where(name);
        }

        // if there is filter by levelName
        if (filter != null && filter.getProgrammingLanguageName() != null) {
            CustomSpecificationQuestion levelName = new CustomSpecificationQuestion("programmingLanguageName", filter.getProgrammingLanguageName());
            if (where == null) {
                where = levelName;
            } else {
                where = where.and(levelName);
            }
        }

        return where;
    }

}
@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecificationQuestion implements Specification {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    public CustomSpecificationQuestion(String string, String search) {
        this.field = string;
        this.value = search;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }
        if (field.equalsIgnoreCase("programmingLanguageName")) {
            return criteriaBuilder.like(root.get("programmingLanguage").get("name"), "%" + value.toString() + "%");}

        return null;
    }
}
