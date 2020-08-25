package ir.maktab.model.repository;

import ir.maktab.model.entity.User;
import ir.maktab.util.UserRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecifications {

    static Specification<User> findMaxMatch(String name,
                                            String family,
                                            String email,
                                            UserRole role) {
        return (Specification<User>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<User> resultCriteria = builder.createQuery(User.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (!StringUtils.isEmpty(name) && name != null) {
                predicates.add(builder.equal(root.get("name"), name));
            }
            if (!StringUtils.isEmpty(family) && family != null) {
                predicates.add(builder.equal(root.get("family"), family));
            }
            if (!StringUtils.isEmpty(email) && email != null) {
                predicates.add(builder.equal(root.get("email"), email));
            }
            if (!StringUtils.isEmpty(role) && role != null) {
                predicates.add(builder.equal(root.get("role"), role));
            }
            predicates.add(builder.notEqual(root.get("role"), UserRole.ADMIN));

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}
