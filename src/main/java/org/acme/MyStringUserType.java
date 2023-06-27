package org.acme;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class MyStringUserType implements UserType<MyStringWrapper> {

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<MyStringWrapper> returnedClass() {
        return MyStringWrapper.class;
    }

    @Override
    public boolean equals(
            final MyStringWrapper x,
            final MyStringWrapper y
    ) {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(final MyStringWrapper x) {
        return Objects.hashCode(x);
    }

    @Override
    public MyStringWrapper nullSafeGet(
            final ResultSet rs,
            final int position,
            final SharedSessionContractImplementor session,
            final Object owner
    ) throws SQLException {
        String columnValue = rs.getString(position);
        if (rs.wasNull()) {
            return null;
        }
        return new MyStringWrapper(columnValue + " (read)");
    }

    @Override
    public void nullSafeSet(
            final PreparedStatement st,
            final MyStringWrapper value,
            final int index,
            final SharedSessionContractImplementor session
    ) throws SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setString(index, value.getField() + " (write)");
        }
    }

    @Override
    public MyStringWrapper deepCopy(final MyStringWrapper value) {
        return value == null ? null : new MyStringWrapper(value.getField());
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(final MyStringWrapper value) {
        return deepCopy(value);
    }

    @Override
    public MyStringWrapper assemble(
            final Serializable cached,
            final Object owner
    ) {
        return deepCopy((MyStringWrapper) cached);
    }
}
