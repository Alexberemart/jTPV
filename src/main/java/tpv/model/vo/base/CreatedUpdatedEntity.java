package tpv.model.vo.base;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class CreatedUpdatedEntity implements Cloneable, Serializable {

    @JsonProperty
    protected Date created;

    @JsonProperty
    protected Date updated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
