package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "leave_request")
public class LeaveRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "request_date")
    private Instant requestDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @Column(name = "status", columnDefinition = "LEAVE_REQUEST_STATUS")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private LeaveRequestStatus status;
    @Column(name = "comment")
    private String comment;

}
