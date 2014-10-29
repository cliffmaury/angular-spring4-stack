package fr.valtech.angularspring.app.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cliff.maury on 28/10/2014.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        if (this.id != null && ((BaseEntity) obj).id != null) {
            return Long.compare(this.id, ((BaseEntity) obj).id) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
