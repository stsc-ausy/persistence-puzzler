package workshop.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detail")
public class Detail {
    @Id
    @Column(name = "object_id")
    private Integer objectId;

    @Column(name = "master_id", nullable = false)
    private Integer masterId;

    @Column(name = "mod_counter", nullable = false)
    private Integer modCounter;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getModCounter() {
        return modCounter;
    }

    public void setModCounter(Integer modCounter) {
        this.modCounter = modCounter;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "objectId=" + objectId +
                ", masterId=" + masterId +
                ", modCounter=" + modCounter +
                '}';
    }
}
