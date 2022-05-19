package digital.windmill.audra.service;

import digital.windmill.audra.dao.entity.VacancyEntity;
import digital.windmill.audra.dao.repository.VacancyRepository;
import digital.windmill.audra.exception.DataNotFoundException;
import digital.windmill.audra.graphql.mapper.VacancyMapper;
import digital.windmill.audra.graphql.type.Vacancy;
import digital.windmill.audra.graphql.type.input.VacanciesInput;
import digital.windmill.audra.service.impl.VacancyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

    private static final UUID VACANCY_UUID = UUID.fromString("91817d8b-5851-413f-9fd5-31f64cef4692");

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private VacancyMapper vacancyMapper;

    @InjectMocks
    private VacancyServiceImpl service;

    @Test
    void shouldReturnVacancyById(@Mock VacancyEntity vacancyEntity) {
        when(vacancyRepository.findVacancyByUuid(VACANCY_UUID)).thenReturn(Optional.of(vacancyEntity));
        var actualResult = service.findVacancyByUuid(VACANCY_UUID);
        assertEquals(vacancyEntity, actualResult);
    }

    @Test
    void shouldThrowDataNotFoundWhenAssetInputIsNull() {
        Assertions.assertThrows(DataNotFoundException.class, () -> service.findVacancyByUuid(null));
    }

    @Test
    void shouldGetAllVacancies(@Mock VacanciesInput vacanciesInput,
                               @Mock VacancyEntity vacancyEntity,
                               @Mock Vacancy vacancy) {
        when(vacancyRepository.findAll((Specification<VacancyEntity>) any(), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(vacancyEntity)));
        when(vacancyMapper.mapVacancyEntityToVacancy(vacancyEntity)).thenReturn(vacancy);

        var actualResult = service.findAllVacancies(vacanciesInput);

        assertEquals(new PageImpl<>(List.of(vacancy)), actualResult);
    }

    @Test
    void shouldSave(@Mock VacancyEntity vacancyEntity) {
        when(vacancyRepository.save(vacancyEntity)).thenReturn(vacancyEntity);
        var actualResult = service.save(vacancyEntity);
        assertEquals(vacancyEntity, actualResult);
    }

}
