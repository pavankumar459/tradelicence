package org.egov.tradelicense.repository.builder;

/**
 * This Class contains INSERT, UPDATE and SELECT queries for FeeMatrix API's
 * 
 * @author Pavan Kumar Kamma
 */
public class FeeMatrixQueryBuilder {

	public static final String INSERT_FEE_MATRIX_QUERY = "INSERT INTO egtl_mstr_fee_matrix"
			+ " (tenantId, applicationType, categoryId, businessNature, subCategoryId, financialYear, effectiveFrom, effectiveTo, createdBy, lastModifiedBy, createdTime, lastModifiedTime)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String INSERT_FEE_MATRIX_DETAIL_QUERY = "INSERT INTO egtl_fee_matrix_details"
			+ " (feeMatrixId, uomFrom, uomTo, amount)" + " VALUES(?,?,?,?)";

}