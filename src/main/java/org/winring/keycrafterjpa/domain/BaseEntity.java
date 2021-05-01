package org.winring.keycrafterjpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@ToString
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected <T, S, V extends Collection<T>> void setRelatedEntity(
            T _this, S _thisMany, V _thisOnesCollection, V newOnesCollection) {
        if (_thisMany != null) {
            _thisOnesCollection.remove(_this);
        }

        if (newOnesCollection != null && !newOnesCollection.contains(_this)) {
            newOnesCollection.add(_this);
        }
    }
}
