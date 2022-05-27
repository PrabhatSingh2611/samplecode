package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.EndOfYearAction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "leave_type")
public class LeaveTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "days")
    private Integer days;
    @Column(name = "end_of_year_action", columnDefinition = "END_OF_YEAR_ACTION")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private EndOfYearAction action;
}
