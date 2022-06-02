package digital.windmill.audra.dao;

import digital.windmill.audra.dao.entity.CandidateEntity;
import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import digital.windmill.audra.graphql.type.input.NodeInput;
import digital.windmill.audra.graphql.type.input.VacancyCandidateWhereInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


public class CandidateSpecification {

    public static Specification<CandidateEntity> byCandidatesInput(VacancyCandidateWhereInput where) {
        var vacancy = Optional.ofNullable(where).map(VacancyCandidateWhereInput::getVacancy).orElse(null);
        var statuses = Optional.ofNullable(where).map(VacancyCandidateWhereInput::getStatus).orElse(null);

        return Specification.where(
                byVacancy(vacancy).and(byStatuses(statuses)));
    }

    public static Specification<CandidateEntity> byVacancy(NodeInput vacancy) {
        return (root, query, builder) -> {
            if (vacancy == null) {
                return builder.conjunction();
            }
            var vacancyJoin = root.join("vacancy");
            return builder.in(vacancyJoin.get("uuid")).value(vacancy.getId());
        };
    }

    public static Specification<CandidateEntity> byStatuses(List<CandidateStatus> statuses) {
        return (root, query, builder) -> {
            if (CollectionUtils.isEmpty(statuses)) {
                return builder.conjunction();
            }
            return builder.in(root.get("status")).value(statuses);
        };
    }

}

