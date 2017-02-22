package org.cendra.eadministration.pdf.services;

import java.io.File;
import java.io.IOException;

import org.cendra.eadministration.pdf.business.EexpBuilder;
import org.cendra.eadministration.pdf.business.EexpPageBuilder;
import org.cendra.eadministration.pdf.business.PdfA3aBuilder;
import org.cendra.eadministration.pdf.model.md.AdminUnit;
import org.cendra.eadministration.pdf.model.md.Eexp;
import org.cendra.eadministration.pdf.model.md.EexpPage;
import org.cendra.eadministration.pdf.model.md.Person;
import org.cendra.eadministration.pdf.model.md.PersonIdentity;
import org.cendra.eadministration.pdf.model.md.User;
import org.cendra.eadministration.pdf.model.md.json.EExpJson;
import org.cendra.eadministration.pdf.model.md.json.PersonIdentityJson;
import org.cendra.eadministration.pdf.model.md.json.ResultJson;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

public class EExpServices {

	public String createEExp(EExpJson eExp) throws IOException {
		try {

			File directoryImg = new File(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_IMG));

			Eexp eexp = new Eexp();

			// ================================================================

			eexp.setCudap(eExp.getObjName());
			eexp.setSubject(eExp.getSubject());
			eexp.setCausative(eExp.getCausative());

			File file = SO.parseB64AndcreateFileTemp(eexp.getCudapFile() + "_institutional_img_to_embed", "png", directoryImg, eExp.getInstitutionalImgToEmbed().getEmbedB64());
			eexp.setPathImg(file.getAbsolutePath());

			AdminUnit udminUnit = new AdminUnit("Expediente Elecrónico");
			udminUnit.setCode(eExp.getAdminUnitCreate().getCode());
			udminUnit.setName(eExp.getAdminUnitCreate().getName());
			udminUnit.setShortName(eExp.getAdminUnitCreate().getShortName());

			file = SO.parseB64AndcreateFileTemp(eexp.getCudapFile() + "_institutional_img_to_embed", "png", directoryImg, eExp.getAdminUnitCreate().getInstitutionalImgToEmbed().getEmbedB64());
			udminUnit.setPathImg(file.getAbsolutePath());

			eexp.setAdminUnit(udminUnit);

			Person person = new Person("Expediente Elecrónico");
			person.setId(eExp.getUserCreate().getPerson().getId());

			PersonIdentity[] identities = new PersonIdentity[eExp.getUserCreate().getPerson().getIdentities().length];

			for (int i = 0; i < eExp.getUserCreate().getPerson().getIdentities().length; i++) {

				PersonIdentityJson item = eExp.getUserCreate().getPerson().getIdentities()[i];

				PersonIdentity personIdentity = new PersonIdentity("Expediente Elecrónico");
				personIdentity.setCode(item.getCode());
				personIdentity.setName(item.getName());
				personIdentity.setShortName(item.getShortName());
				personIdentity.setNumber(item.getNumber());

				identities[i] = personIdentity;
			}

			person.setIdentities(identities);
			person.setNames(eExp.getUserCreate().getPerson().getNames());
			person.setLastNames(eExp.getUserCreate().getPerson().getLastNames());

			User user = new User("Expediente Elecrónico");
			user.setId(eExp.getUserCreate().getId());
			user.setName(eExp.getUserCreate().getName());
			user.setPerson(person);

			eexp.setUser(user);

			eexp.seteExp(eExp);

			// ================================================================

			EexpBuilder eexpBuilder = new EexpBuilder();

			String pathFile = eexpBuilder.build(eexp);

			ResultJson result = new ResultJson();

			result.setPdfB64(SO.convertB64(pathFile));

			// SO.parseB64AndcreateFileTemp("x", "pdf", new File("/home/java/Escritorio/"), result.getPdfB64());

			return result.toString();

		} catch (Exception e) {

			e.printStackTrace();

			return e.toString();
		}
	}

	public String appendPageEExp(EExpJson eExp) throws IOException {
		try {
			EexpPageBuilder eexpPageBuilder = new EexpPageBuilder();

			EexpPage eexpPage = new EexpPage();

			eexpPage.setPathImg("PdfA3aBuilder.PATH_RESOURCES_IMG" + "unc.png");

			eexpPage.setPathSourceEe("eePathResult");
			eexpPage.setPathSourceEd("edog.getPathResultPdf()");

			// eexpPage.setAdminUnit(au);
			eexpPage.setUser(null);

			String eePathResult = eexpPageBuilder.build(eexpPage);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;

	}

}
