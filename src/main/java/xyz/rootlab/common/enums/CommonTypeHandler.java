package xyz.rootlab.common.enums;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CommonTypeHandler<E extends EntityEnumerable> extends BaseTypeHandler<EntityEnumerable> {

    private final Class<E> type;

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EntityEnumerable entityEnumerable, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, entityEnumerable.getCode());
    }

    @Override
    public EntityEnumerable getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return getEnumByCode(resultSet.getString(s));
    }

    @Override
    public EntityEnumerable getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return getEnumByCode(resultSet.getString(i));
    }

    @Override
    public EntityEnumerable getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return getEnumByCode(callableStatement.getString(i));
    }

    private EntityEnumerable getEnumByCode(String code) {
        try {
            EntityEnumerable[] codeTypes = type.getEnumConstants();
            for (EntityEnumerable temp : codeTypes) {
                if (temp.getCode().equals(code)) {
                    return temp;
                }
            }
        } catch (Exception e) {
            throw new TypeException("Can't make enum object '" + type + "'", e);
        }
        return null;
    }

}