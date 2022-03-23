package digital.windmill.audra.graphql.type;

import java.util.List;

public class EmployeeConnectionPayload extends ConnectionPayload<Employee> {

    @Override
    public List<Employee> getItems() {
        return List.of();
    }
    
    @Override
    public PageInfo getPageInfo() {
        return EMPTY;
    }
    
    @Override
    public Long getTotalItems() {
        return (long) -1;
    }
}
