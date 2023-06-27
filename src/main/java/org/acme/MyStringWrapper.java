package org.acme;

import java.io.Serializable;

public class MyStringWrapper implements Serializable {

    public String field;


    public MyStringWrapper(final String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(final String field) {
        this.field = field;
    }
}
