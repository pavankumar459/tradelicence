package org.egov.tradelicense.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.egov.models.AuditDetails;
import org.egov.models.Category;
import org.egov.models.CategoryRequest;
import org.egov.models.CategoryResponse;
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.tradelicense.TradeLicenseApplication;
import org.egov.tradelicense.config.PropertiesManager;
import org.egov.tradelicense.services.CategoryService;
import org.egov.tradelicense.services.DocumentTypeService;
import org.egov.tradelicense.services.PenaltyRateService;
import org.egov.tradelicense.services.UOMService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeLicenseMasterController.class)
@ContextConfiguration(classes = { TradeLicenseApplication.class })
public class CategoryContollerTest {

	@MockBean
	private CategoryService categoryService;

	@MockBean
	private UOMService uomService;

	@MockBean
	private PenaltyRateService penaltyRateService;
	
	@MockBean
	DocumentTypeService documentTypeService;
	
	@MockBean
	private PropertiesManager propertiesManager;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateCategory() throws Exception {

		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		category.setAuditDetails(auditDetails);

		CategoryResponse categoryResponse = new CategoryResponse();
		categories.add(category);

		categoryResponse.setResponseInfo(new ResponseInfo());
		categoryResponse.setCategories(categories);

		try {

			when(categoryService.createCategoryMaster(any(CategoryRequest.class)))
			.thenReturn(categoryResponse);

			mockMvc.perform(post("/tradelicense/category/_create").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("categoryCreateRequest.json")))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().json(getFileContents("categoryCreateResponse.json")));

		} catch (Exception e) {

			assertTrue(Boolean.FALSE);
		}

		assertTrue(Boolean.TRUE);

	}

	@Test
	public void testUpdateCategory() throws Exception {

		CategoryResponse categoryResponse = new CategoryResponse();
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		category.setAuditDetails(auditDetails);

		categories.add(category);

		categoryResponse.setResponseInfo(new ResponseInfo());
		categoryResponse.setCategories(categories);

		try {

			when(categoryService.updateCategoryMaster(any(CategoryRequest.class))).thenReturn(categoryResponse);
			mockMvc.perform(post("/tradelicense/category/_update").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("categoryUpdateRequest.json")))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().json(getFileContents("categoryUpdateResponse.json")));

		} catch (Exception e) {

			assertTrue(Boolean.FALSE);
			e.printStackTrace();
		}

		assertTrue(Boolean.TRUE);

	}

	@Test
	public void testSearchCategory() throws Exception {

		CategoryResponse categoryResponse = new CategoryResponse();
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		category.setAuditDetails(auditDetails);

		categories.add(category);

		categoryResponse.setResponseInfo(new ResponseInfo());
		categoryResponse.setCategories(categories);

		try {

			when(categoryService.getCategoryMaster(any(RequestInfo.class), any(String.class), any(Integer[].class),
					any(String.class), any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
			.thenReturn(categoryResponse);

			mockMvc.perform(post("/tradelicense/category/_search").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("categorySearchRequest.json")))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().json(getFileContents("categorySearchResponse.json")));

		} catch (Exception e) {

			assertTrue(Boolean.FALSE);
			e.printStackTrace();
		}

		assertTrue(Boolean.TRUE);

	}

	private String getFileContents(String fileName) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		return new String(Files.readAllBytes(new File(classLoader.getResource(fileName).getFile()).toPath()));
	}
}