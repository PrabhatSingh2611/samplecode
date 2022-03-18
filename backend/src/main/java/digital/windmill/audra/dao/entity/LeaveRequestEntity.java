package digital.windmill.audra.dao.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import digital.windmill.audra.dao.entity.enums.LeaveRequestStatus;
import lombok.Getter;
import lombok.Setter;

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
