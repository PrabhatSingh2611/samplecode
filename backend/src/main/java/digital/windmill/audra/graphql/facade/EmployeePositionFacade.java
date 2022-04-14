package digital.windmill.audra.graphql.facade;


import digital.windmill.audra.dao.entity.EmployeePositionEntity;
import digital.windmill.audra.graphql.mapper.EmployeePositionMapper;
import digital.windmill.audra.graphql.type.EmployeePosition;
import digital.windmill.audra.graphql.type.input.CreateEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.DeleteEmployeePositionInput;
import digital.windmill.audra.graphql.type.input.UpdateEmployeePositionInput;
import digital.windmill.audra.service.EmployeePositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeePositionFacade {
    private EmployeePositionService employeePositionServiceImpl;
    private EmployeePositionMapper employeePositionMapper;

    public EmployeePosition createEmployeePosition(CreateEmployeePositionInput input) {
        return employeePositionMapper
                .map(employeePositionServiceImpl
                        .createEmployeePosition(EmployeePositionEntity
                                .builder()
                                .uuid(UUID.randomUUID())
                                .name(input.getName())
                                .build()));
    }

    public EmployeePosition updateEmployeePosition(UpdateEmployeePositionInput input) {
        return employeePositionMapper
                .map(employeePositionServiceImpl
                        .updateEmployeePosition(EmployeePosition
                                .builder()
                                .uuid(input.getUuid())
                                .name(input.getName())
                                .build()));
    }

    public EmployeePosition deleteEmployeePosition(DeleteEmployeePositionInput input) {
        return employeePositionMapper
                .map(employeePositionServiceImpl
                        .deleteEmployeePosition(EmployeePosition
                                .builder()
                                .uuid(input.getUuid())
                                .build()));
    }

}
