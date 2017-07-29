package org.egov.tradelicense.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.egov.models.AuditDetails;
import org.egov.models.FeeMatrix;
import org.egov.models.FeeMatrixDetail;
import org.egov.tradelicense.repository.builder.FeeMatrixQueryBuilder;
import org.egov.tradelicense.utility.TimeStampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Repository class for create/update/search FeeMatrix master
 * 
 * @author Pavan Kumar Kamma
 *
 */

@Repository
public class FeeMatrixRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Description : this method will create FeeMatrix in database
	 * 
	 * @param FeeMatrix
	 * @return feeMatrixId
	 */
	public Long createFeeMatrix(String tenantId, FeeMatrix feeMatrix) {

		AuditDetails auditDetails = feeMatrix.getAuditDetails();
		String feeMatrixInsertQuery = FeeMatrixQueryBuilder.INSERT_FEE_MATRIX_QUERY;
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(feeMatrixInsertQuery, new String[] { "id" });

				ps.setString(1, feeMatrix.getTenantId());
				ps.setString(2, feeMatrix.getApplicationType().toString());
				ps.setLong(3, feeMatrix.getCategoryId());
				ps.setString(4, feeMatrix.getBusinessNature().toString());
				ps.setLong(5, feeMatrix.getSubCategoryId());
				ps.setString(6, feeMatrix.getFinancialYear());
				ps.setTimestamp(7, TimeStampUtil.getTimeStamp(feeMatrix.getEffectiveFrom()));
				ps.setTimestamp(8, TimeStampUtil.getTimeStamp(feeMatrix.getEffectiveTo()));
				ps.setString(9, auditDetails.getCreatedBy());
				ps.setString(10, auditDetails.getLastModifiedBy());
				ps.setLong(11, auditDetails.getCreatedTime());
				ps.setLong(12, auditDetails.getLastModifiedTime());

				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());
	}

	/**
	 * Description : this method will create FeeMatrixDetail in database
	 * 
	 * @param FeeMatrixDetail
	 * @return feeMatrixDetailId
	 */
	public Long createFeeMatrixDetails(String tenantId, FeeMatrixDetail feeMatrixDetail) {

		String feeMatrixInsertQuery = FeeMatrixQueryBuilder.INSERT_FEE_MATRIX_DETAIL_QUERY;
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(feeMatrixInsertQuery, new String[] { "id" });

				ps.setLong(1, feeMatrixDetail.getFeeMatrixId());
				ps.setLong(2, feeMatrixDetail.getUomFrom());
				ps.setLong(3, feeMatrixDetail.getUomTo());
				ps.setDouble(4, feeMatrixDetail.getAmount());

				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, holder);

		return Long.valueOf(holder.getKey().intValue());
	}
}