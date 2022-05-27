package digital.windmill.audra.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "playbook_task_list")
public class PlaybookTaskListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @OneToMany(mappedBy = "playbookTaskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaybookTaskEntity> tasks;
}
