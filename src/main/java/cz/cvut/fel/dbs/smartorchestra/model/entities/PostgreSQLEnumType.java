/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.model.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

/**
 * This class is used for mapping an enum to PostgreSQL database enum
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class PostgreSQLEnumType extends EnumType{
    /**
     * See {@link org.hibernate.type.EnumType} for more information.
     * @param st
     * @param value
     * @param index
     * @param session
     * @throws SQLException
     * @throws HibernateException 
     */
    @Override
    public void nullSafeSet(
            PreparedStatement st,
            Object value,
            int index,
            SharedSessionContractImplementor session
    ) throws SQLException, HibernateException{
        if(value == null){
            st.setNull(index, Types.OTHER);
        } else {
            st.setObject(index, value.toString(), Types.OTHER);
        }     
    }
}
