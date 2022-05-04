package digital.windmill.audra.graphql.resolver.vacancy;

import digital.windmill.audra.graphql.facade.VacancyFacade;
import digital.windmill.audra.graphql.type.ConnectionPayload;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.VacancyPayload;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.graphql.type.input.VacancyInput;
import digital.windmill.audra.graphql.utils.ConnectionUtils;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VacancyResolver implements GraphQLQueryResolver {

    private VacancyFacade vacancyFacade;

    public VacancyPayload vacancy(VacancyInput input) {
        return VacancyPayload
                .builder()
                .item(vacancyFacade.findVacancyByUuid(input.getUuid()))
                .build();
    }

    public ConnectionPayload<Vacancy> vacancies(VacanciesInput input) {
        return ConnectionUtils.buildPayload(vacancyFacade.getVacancies(input));
    }
}