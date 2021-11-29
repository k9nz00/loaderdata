package com.semka.loaderdata.dao.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public abstract class AbstractUserType implements UserType {
    @Override
    public Object nullSafeGet(final ResultSet resultSet,
                              final String[] strings,
                              final SharedSessionContractImplementor sharedSessionContractImplementor,
                              final Object o) throws SQLException {
        return strings.length > 0 ? resultSet.getArray(strings[0]).getArray() : null;

    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement,
                            final Object o,
                            final int i,
                            final SharedSessionContractImplementor sharedSessionContractImplementor)
            throws SQLException {
        if (o == null) {
            preparedStatement.setNull(i, Types.OTHER);
        } else {
            Array array = sharedSessionContractImplementor.connection().createArrayOf(getTypeName(), (String[]) o);
            preparedStatement.setObject(i, array);
        }
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.OTHER};
    }

    @Override
    public boolean equals(final Object o, final Object o1) {
        return Objects.equals(o, o1);
    }

    @Override
    public int hashCode(final Object o) {
        return o.hashCode();
    }

    @Override
    public Object deepCopy(final Object o) {
        return o;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(final Serializable serializable, final Object o) {
        return o;
    }

    @Override
    public Object replace(final Object o, final Object o1, final Object o2) {
        return o;
    }

    public abstract String getTypeName();
}
