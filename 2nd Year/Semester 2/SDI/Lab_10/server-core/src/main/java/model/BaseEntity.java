package model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author radu.
 */
@MappedSuperclass
public class BaseEntity<ID> implements Serializable {
    @Id
    private ID id;

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + this.id +
                '}';
    }
}
