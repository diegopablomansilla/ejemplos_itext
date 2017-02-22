package org.cendra.eadministration.pdf.services;

import java.io.File;
import java.io.IOException;

import org.cendra.eadministration.pdf.business.EdogBuilder;
import org.cendra.eadministration.pdf.model.md.AdminUnit;
import org.cendra.eadministration.pdf.model.md.Edog;
import org.cendra.eadministration.pdf.model.md.Person;
import org.cendra.eadministration.pdf.model.md.PersonIdentity;
import org.cendra.eadministration.pdf.model.md.User;
import org.cendra.eadministration.pdf.model.md.json.EDogJson;
import org.cendra.eadministration.pdf.model.md.json.PersonIdentityJson;
import org.cendra.eadministration.pdf.model.md.json.ResultJson;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

public class EDogServices {

	public String createEDog(EDogJson eDog) throws IOException {
		try {

			File directoryPdf = new File(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_PDF));
			File directoryImg = new File(GlobalProperties.get(GlobalProperties.PATH_RESOURCES_IMG));

			Edog edog = new Edog();

			// ================================================================

			edog.setCudap(eDog.getObjName());
			edog.setSubject(eDog.getSubject());

			File file = SO.parseB64AndcreateFileTemp(edog.getCudapFile() + "_pdf_to_embed", "pdf", directoryPdf, eDog.getPdfToEmbed().getEmbedB64());
			System.out.println(file.getAbsolutePath());
			edog.setPathSource(file.getAbsolutePath());

			file = SO.parseB64AndcreateFileTemp(edog.getCudapFile() + "_institutional_img_to_embed", "png", directoryImg, eDog.getInstitutionalImgToEmbed().getEmbedB64());
			edog.setPathImg(file.getAbsolutePath());

			AdminUnit udminUnit = new AdminUnit("Documento General");
			udminUnit.setCode(eDog.getAdminUnitCreate().getCode());
			udminUnit.setName(eDog.getAdminUnitCreate().getName());
			udminUnit.setShortName(eDog.getAdminUnitCreate().getShortName());

			file = SO.parseB64AndcreateFileTemp(edog.getCudapFile() + "_institutional_img_to_embed", "png", directoryImg, eDog.getAdminUnitCreate().getInstitutionalImgToEmbed().getEmbedB64());
			udminUnit.setPathImg(file.getAbsolutePath());

			edog.setAdminUnit(udminUnit);

			Person person = new Person("Documento General");
			person.setId(eDog.getUserCreate().getPerson().getId());

			PersonIdentity[] identities = new PersonIdentity[eDog.getUserCreate().getPerson().getIdentities().length];

			for (int i = 0; i < eDog.getUserCreate().getPerson().getIdentities().length; i++) {

				PersonIdentityJson item = eDog.getUserCreate().getPerson().getIdentities()[i];

				PersonIdentity personIdentity = new PersonIdentity("Documento General");
				personIdentity.setCode(item.getCode());
				personIdentity.setName(item.getName());
				personIdentity.setShortName(item.getShortName());
				personIdentity.setNumber(item.getNumber());

				identities[i] = personIdentity;
			}

			person.setIdentities(identities);
			person.setNames(eDog.getUserCreate().getPerson().getNames());
			person.setLastNames(eDog.getUserCreate().getPerson().getLastNames());

			User user = new User("Documento General");
			user.setId(eDog.getUserCreate().getId());
			user.setName(eDog.getUserCreate().getName());
			user.setPerson(person);

			edog.setUser(user);

			edog.seteDog(eDog);

			// ================================================================

			EdogBuilder edogBuilder = new EdogBuilder();

			String pathFile = edogBuilder.build(edog);
			
			ResultJson result = new ResultJson();
			
			result.setPdfB64(SO.convertB64(pathFile));

//			SO.parseB64AndcreateFileTemp("x", "pdf", new File("/home/java/Escritorio/"), result.getPdfB64());
			
			return result.toString();

		} catch (Exception e) {

			e.printStackTrace();
			
			return e.toString();
		}

		

	}
}
