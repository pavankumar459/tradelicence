package org.egov.tradelicence.controller;

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
import org.egov.models.RequestInfo;
import org.egov.models.ResponseInfo;
import org.egov.models.UOM;
import org.egov.models.UOMRequest;
import org.egov.models.UOMResponse;
import org.egov.tradelicence.TradeLicenceApplication;
import org.egov.tradelicence.services.MasterService;
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
@WebMvcTest(TradeLicenceMasterController.class)
@ContextConfiguration(classes = { TradeLicenceApplication.class })
public class UOMControllerTest {

	@MockBean
	private MasterService masterService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateUom() throws Exception {

		List<UOM> uoms = new ArrayList<>();
		UOM uom = new UOM();
		uom.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		uom.setAuditDetails(auditDetails);

		UOMResponse uomResponse = new UOMResponse();
		uoms.add(uom);

		uomResponse.setResponseInfo(new ResponseInfo());
		uomResponse.setUoms(uoms);

		try {

			when(masterService.createUomMaster(any(String.class), any(UOMRequest.class))).thenReturn(uomResponse);

			mockMvc.perform(post("/tradelicence/uom/_create").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("uomCreateRequest.json")))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(content().json(getFileContents("uomCreateResponse.json")));

		} catch (Exception e) {

			assertTrue(Boolean.FALSE);
		}

		assertTrue(Boolean.TRUE);

	}

	@Test
	public void testUpdateUom() throws Exception {

		UOMResponse uomResponse = new UOMResponse();
		List<UOM> uoms = new ArrayList<>();
		UOM uom = new UOM();
		uom.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		uom.setAuditDetails(auditDetails);

		uoms.add(uom);

		uomResponse.setResponseInfo(new ResponseInfo());
		uomResponse.setUoms(uoms);

		try {

			when(masterService.updateUomMaster(any(UOMRequest.class))).thenReturn(uomResponse);
			mockMvc.perform(post("/tradelicence/uom/_update").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("uomUpdateRequest.json")))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(content().json(getFileContents("uomUpdateResponse.json")));

		} catch (Exception e) {

			assertTrue(Boolean.FALSE);
			e.printStackTrace();
		}

		assertTrue(Boolean.TRUE);

	}

	@Test
	public void testSearchUom() throws Exception {

		UOMResponse uomResponse = new UOMResponse();
		List<UOM> uoms = new ArrayList<>();
		UOM uom = new UOM();
		uom.setTenantId("default");

		AuditDetails auditDetails = new AuditDetails();
		uom.setAuditDetails(auditDetails);

		uoms.add(uom);

		uomResponse.setResponseInfo(new ResponseInfo());
		uomResponse.setUoms(uoms);

		try {

			when(masterService.getUomMaster(any(RequestInfo.class), any(String.class), any(Integer[].class),
					any(String.class), any(String.class), any(Boolean.class), any(Integer.class), any(Integer.class)))
							.thenReturn(uomResponse);

			mockMvc.perform(post("/tradelicence/uom/_search").param("tenantId", "default")
					.contentType(MediaType.APPLICATION_JSON).content(getFileContents("uomSearchRequest.json")))
					.andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(content().json(getFileContents("uomSearchResponse.json")));

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