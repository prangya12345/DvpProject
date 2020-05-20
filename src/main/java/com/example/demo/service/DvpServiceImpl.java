package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.model.EntityData;
import com.example.demo.repository.DvpRepository;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

@Service
public class DvpServiceImpl implements DvpService{

	@Autowired
	DvpRepository dvpRepository;

	@Override
	public EntityData getElectricityDetails(String id){

		Optional<EntityData> d=dvpRepository.findById(id);
		if(d.isPresent()) {
			System.out.println("dataa is fetching form database");
			return d.get();
            }


		EntityData e= new EntityData();
		String baseUrl="https://www.tssouthernpower.com/CPDCL_Home.portal?_nfpb=true&_pageLabel=CPDCL_Home_portal_page_109";
		try(WebClient client=new WebClient();){
			client.getOptions().setJavaScriptEnabled(false);
			client.getOptions().setCssEnabled(false);
			client.getOptions().setUseInsecureSSL(true);

			HtmlPage page=client.getPage(baseUrl);
			HtmlInput hm=(HtmlInput)page.getElementByName("OnlineBillEnquiry_LeftPanel_3uscno");
			hm.setValueAttribute(id);
			e.setId(id);

			/*
			 * List<DomElement> hi=page.getElementsByTagName("submit"); List<DomElement>
			 * hi1=page.getElementsByTagName("Submit"); List<DomElement>
			 * hi2=page.getElementsByTagName("input"); List<DomElement>
			 * hi3=page.getElementsByTagName("submit-btn");
			 */
			List<DomElement> hi4=page.getByXPath("//input[@class='submit-btn']");
			HtmlSubmitInput hs=(HtmlSubmitInput) hi4.get(0);
			HtmlPage page1=hs.click();
			if(page1.asText().contains("Data not available")) {
				System.out.println("Data Not available");
				return null;
			}

			List<DomElement> tableList=page1.getByXPath("//table[@class='Table_Border1 innertextNew']");
			HtmlTable  table=(HtmlTable)tableList.get(0);
			for (final HtmlTableRow row : table.getRows()) {

				List<HtmlTableCell> cells= row.getCells();
				System.out.println(cells.size());
				if(cells.size()>1) {
					if(cells.get(0).asText().contains("Consumer Name")) {
						e.setName(cells.get(1).asText());
					}if(cells.get(0).asText().contains("Address")) {
						e.setAddress(cells.get(1).asText());
					} if(cells.get(1).asText().contains("Unique Service No.")) {
						e.setAddress(cells.get(2).asText());}
				}
			}


		} catch (IOException e1) {
			e1.printStackTrace();
		}

		dvpRepository.save(e);
		return e;
	}





}
