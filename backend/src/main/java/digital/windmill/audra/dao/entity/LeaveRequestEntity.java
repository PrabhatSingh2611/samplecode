package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "leave_request")
public class LeaveRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @Column(name = "status", columnDefinition = "LEAVE_REQUEST_STATUS")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private LeaveRequestStatus status;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveTypeEntity type;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;
    @Column(name = "request_date")
    @CreationTimestamp
    private Instant requestDate;
    @Formula("CASE\n"
            + "  WHEN (status = 'NEW') THEN 1\n"
            + "  ELSE 0\n"
            + " END")
    private int orderByPending;


}
