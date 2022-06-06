package digital.windmill.audra.feed.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import digital.windmill.audra.dao.entity.EmployeeEntity;
import digital.windmill.audra.dao.entity.ResourceEntity;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
@Table( name = "post")
public class PostEntity extends FeedItemEntity {

    @Column(name = "text")
    private String text;
    @OneToMany
    @JoinTable(name = "post_to_resource",
        joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id")
    )
    private List<ResourceEntity> images;
    @OneToOne
    @JoinColumn(name = "author_id", updatable = false)
    private EmployeeEntity author;

}