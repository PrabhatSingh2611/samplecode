package digital.windmill.audra.graphql.resolver.vacancy;

import digital.windmill.audra.graphql.facade.impl.VacancyFacadeImpl;
import digital.windmill.audra.graphql.type.VacancyPayload;
import digital.windmill.audra.graphql.type.input.CreateVacancyInput;
import digital.windmill.audra.graphql.type.input.UpdateVacancyInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VacancyMutationResolver implements GraphQLMutationResolver {

    private VacancyFacadeImpl vacancyFacade;

    public VacancyPayload createVacancy(CreateVacancyInput vacancyInput) {
        return VacancyPayload
                .builder()
                .item(vacancyFacade.createVacancy(vacancyInput))
                .build();
    }

    public VacancyPayload updateVacancy(UpdateVacancyInput input) {
        return VacancyPayload
                .builder()
                .item(vacancyFacade.updateVacancy(input))
                .build();
    }
}
