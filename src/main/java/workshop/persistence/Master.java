package workshop.persistence;

import javax.persistence.*;

@Entity
@Table(name = "master")
public class Master {
    @Id
    @Column(name = "object_id")
    private Integer objectId;

    @Column(name = "reference", nullable = false, unique = true)
    private String reference;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Master{" +
                "objectId=" + objectId +
                ", reference='" + reference + '\'' +
                ", status=" + status +
                '}';
    }
}
