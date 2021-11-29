package com.semka.loaderdata.dao.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.*;

public class AuthoritiesType extends AbstractUserType{
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
            Array array = sharedSessionContractImplementor.connection().createArrayOf("authority", (String[]) o);
            preparedStatement.setObject(i, array);
        }
    }

    @Override
    public String getTypeName() {
        return "authority";
    }

    @Override
    public Class<String[]> returnedClass() {
        return String[].class;
    }
}
