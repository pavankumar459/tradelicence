package org.egov.tradelicense.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.egov.models.BusinessNature;
import org.egov.tradelicense.repository.builder.BusinessNatureQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BusinessNatureRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Description : this method for creating business nature master
	 * 
	 * @param tenantId
	 * @param BusinessNatureRequest
	 * @return BusinessNatureResponse
	 */
	public Long createBusinessNature(String tenantId, BusinessNature businessNature) {

		Long createdTime = new Date().getTime();

		String businessNatureInsertQuery = BusinessNatureQueryBuilder.INSERT_BUSINESS_NATURE_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(businessNatureInsertQuery,
						new String[] { "id" });

				ps.setString(1, businessNature.getTenantId());
				ps.setString(2, businessNature.getCode());
				ps.setString(3, businessNature.getName());
				ps.setString(4, businessNature.getAuditDetails().getCreatedBy());
				ps.setString(5, businessNature.getAuditDetails().getLastModifiedBy());
				ps.setLong(6, createdTime);
				ps.setLong(7, createdTime);
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());

	}

	/**
	 * Description : this method for updating business nature master
	 * 
	 * @param BusinessNature
	 * @return BusinessNature
	 */
	public BusinessNature updateBusinessNature(BusinessNature businessNature) {

		Long updatedTime = new Date().getTime();

		String businessNatureUpdateSql = BusinessNatureQueryBuilder.UPDATE_BUSINESS_NATURE_QUERY;

		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(businessNatureUpdateSql);

				ps.setString(1, businessNature.getTenantId());
				ps.setString(2, businessNature.getCode());
				ps.setString(3, businessNature.getName());
				ps.setString(4, businessNature.getAuditDetails().getLastModifiedBy());
				ps.setLong(5, updatedTime);
				ps.setLong(6, businessNature.getId());

				return ps;
			}
		};

		jdbcTemplate.update(psc);
		return businessNature;
	}
}