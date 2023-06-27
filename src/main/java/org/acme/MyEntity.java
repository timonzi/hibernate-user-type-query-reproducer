package org.acme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class MyEntity {
    @Id
    @GeneratedValue
    public Long id;

    @Type(MyStringUserType.class)
    public MyStringWrapper field;


    public Long getId() {
        return id;
    }

    public MyStringWrapper getField() {
        return field;
    }

    public void setField(final MyStringWrapper field) {
        this.field = field;
    }
}
