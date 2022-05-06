package digital.windmill.audra.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "announcement")
public class AnnouncementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "body")
    private String body;
    @Column(name = "created_at")
    @CreationTimestamp()
    private Instant createdAt;
}
