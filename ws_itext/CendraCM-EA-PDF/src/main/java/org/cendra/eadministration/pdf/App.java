package org.cendra.eadministration.pdf;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.cendra.eadministration.pdf.model.md.json.AdminUnitJson;
import org.cendra.eadministration.pdf.model.md.json.AttachedJson;
import org.cendra.eadministration.pdf.model.md.json.EDogJson;
import org.cendra.eadministration.pdf.model.md.json.EExpJson;
import org.cendra.eadministration.pdf.model.md.json.PersonIdentityJson;
import org.cendra.eadministration.pdf.model.md.json.PersonJson;
import org.cendra.eadministration.pdf.model.md.json.UserJson;
import org.cendra.eadministration.pdf.services.EDogServices;
import org.cendra.eadministration.pdf.services.EExpServices;
import org.cendra.eadministration.pdf.util.GlobalProperties;
import org.cendra.eadministration.pdf.util.SO;

/**
 * Hello world!
 *
 */
@SuppressWarnings("restriction")
public class App {

	public static void main(String[] args) {
		try {

			createEdog111();
			createEdog222();
			createEExp99999999();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	private static void createEdog111() throws IOException {

		EDogJson eDog = new EDogJson();

		// EDOC ========================================================================

		eDog.setId(UUID.randomUUID().toString());
		eDog.setObjImplements(new String[] { UUID.randomUUID().toString() });
		eDog.setObjName("EDOG-UNC:111/2016");
		eDog.setObjTitle("Documento General " + eDog.getObjName());
		eDog.setObjDescription("Documento General");
		eDog.setObjTags(new String[] { eDog.getObjDescription(), eDog.getObjName() });
		eDog.setSubject("Constancia AFIP - Ing. Diego Mansilla");
		// eDog.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		String imgB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "unc.png"));

		AttachedJson attached = new AttachedJson();
		attached.setEmbedB64(imgB64);

		eDog.setInstitutionalImgToEmbed(attached);

		AdminUnitJson adminUnit = new AdminUnitJson();
		adminUnit.setId(UUID.randomUUID().toString());
		adminUnit.setCode("76");
		adminUnit.setShortName("PSI");
		adminUnit.setName("Prosecretaría de Informática");

		String imgAuB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "psi.png"));
		attached = new AttachedJson();
		attached.setEmbedB64(imgAuB64);

		adminUnit.setInstitutionalImgToEmbed(attached);

		eDog.setAdminUnitCreate(adminUnit);

		PersonIdentityJson personIdentity = new PersonIdentityJson();
		personIdentity.setId(UUID.randomUUID().toString());
		personIdentity.setCode("ARG_DNI");
		personIdentity.setName("Documento Nacional de Identidad");
		personIdentity.setShortName("DNI");
		personIdentity.setNumber("27656133");

		PersonJson person = new PersonJson();
		person.setId(UUID.randomUUID().toString());
		person.setIdentities(new PersonIdentityJson[] { personIdentity });
		person.setNames(new String[] { "Diego", "Pablo" });
		person.setLastNames(new String[] { "Mansilla", "Cendra" });

		UserJson user = new UserJson();
		user.setId(UUID.randomUUID().toString());
		user.setName("dmansilla@unc.edu.ar");
		user.setPerson(person);

		eDog.setUserCreate(user);

		// EDOG ========================================================================

		String pdfB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_PDF") + File.separatorChar + "CONSTANCIA AFIP.pdf"));
		attached = new AttachedJson();
		attached.setEmbedB64(pdfB64);
		String id = UUID.randomUUID().toString();
		attached.setId(id);
		attached.setName("CONSTANCIA AFIP.pdf");
		attached.setUrl("https://cendra.unc.edu.ar/open?id=" + id);

		eDog.setPdfToEmbed(attached);

		// ///////////////////////// INPUT ////////////////////////////////////////

		// System.out.println(eDog);

		// ///////////////////////// OUTPUT ////////////////////////////////////////

		EDogServices eDogServices = new EDogServices();
		String json = eDogServices.createEDog(eDog);

		// SO.parseB64AndcreateFileTemp("x", "pdf", new File("/home/java/Escritorio/"), b64)

		// System.out.println(json);
		
	

	}

	@SuppressWarnings("restriction")
	private static void createEdog222() throws IOException {

		EDogJson eDog = new EDogJson();

		// EDOC ========================================================================

		eDog.setId(UUID.randomUUID().toString());
		eDog.setObjImplements(new String[] { UUID.randomUUID().toString() });
		eDog.setObjName("EDOG-UNC:222/2016");
		eDog.setObjTitle("Documento General " + eDog.getObjName());
		eDog.setObjDescription("Documento General");
		eDog.setObjTags(new String[] { eDog.getObjDescription(), eDog.getObjName() });
		eDog.setSubject("CV - Ing. Diego Mansilla");
		// eDog.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		String imgB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "unc.png"));

		AttachedJson attached = new AttachedJson();
		attached.setEmbedB64(imgB64);

		eDog.setInstitutionalImgToEmbed(attached);

		AdminUnitJson adminUnit = new AdminUnitJson();
		adminUnit.setId(UUID.randomUUID().toString());
		adminUnit.setCode("461");
		adminUnit.setShortName("SPyGI");
		adminUnit.setName("Direción General de Contrataciones");

		String imgAuB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "Marca_SPGI_Color.jpg"));
		attached = new AttachedJson();
		attached.setEmbedB64(imgAuB64);

		adminUnit.setInstitutionalImgToEmbed(attached);

		eDog.setAdminUnitCreate(adminUnit);

		PersonIdentityJson personIdentity = new PersonIdentityJson();
		personIdentity.setId(UUID.randomUUID().toString());
		personIdentity.setCode("ARG_DNI");
		personIdentity.setName("Documento Nacional de Identidad");
		personIdentity.setShortName("DNI");
		personIdentity.setNumber("25557181");

		PersonJson person = new PersonJson();
		person.setId(UUID.randomUUID().toString());
		person.setIdentities(new PersonIdentityJson[] { personIdentity });
		person.setNames(new String[] { "Fernanda", "María" });
		person.setLastNames(new String[] { "Carranza" });

		UserJson user = new UserJson();
		user.setId(UUID.randomUUID().toString());
		user.setName("fcarranza@unc.edu.ar");
		user.setPerson(person);

		eDog.setUserCreate(user);

		// EDOG ========================================================================

		String pdfB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_PDF") + File.separatorChar + "cv.pdf"));
		attached = new AttachedJson();
		attached.setEmbedB64(pdfB64);
		String id = UUID.randomUUID().toString();
		attached.setId(id);
		attached.setName("CONSTANCIA AFIP.pdf");
		attached.setUrl("https://cendra.unc.edu.ar/open?id=" + id);

		eDog.setPdfToEmbed(attached);

		// ///////////////////////// INPUT ////////////////////////////////////////

		// System.out.println(eDog);

		// ///////////////////////// OUTPUT ////////////////////////////////////////

		EDogServices eDogServices = new EDogServices();
		String json = eDogServices.createEDog(eDog);

		// SO.parseB64AndcreateFileTemp("x", "pdf", new File("/home/java/Escritorio/"), b64)

		// System.out.println(json);

	}

	@SuppressWarnings("restriction")
	private static void createEExp99999999() throws IOException {

		EExpJson eExp = new EExpJson();

		// EDOC ========================================================================

		eExp.setId(UUID.randomUUID().toString());
		eExp.setObjImplements(new String[] { UUID.randomUUID().toString() });
		eExp.setObjName("EEXP-UNC:99999999/2016");
		eExp.setObjTitle("Expediente Elecrónico " + eExp.getObjName());
		eExp.setObjDescription("Expediente Elecrónico");
		eExp.setObjTags(new String[] { eExp.getObjDescription(), eExp.getObjName() });
		eExp.setSubject("Contrato de Locación de Servicios");
		// eDog.setCreateDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		String imgB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "unc.png"));

		AttachedJson attached = new AttachedJson();
		attached.setEmbedB64(imgB64);

		eExp.setInstitutionalImgToEmbed(attached);

		AdminUnitJson adminUnit = new AdminUnitJson();
		adminUnit.setId(UUID.randomUUID().toString());
		adminUnit.setCode("76");
		adminUnit.setShortName("PSI");
		adminUnit.setName("Prosecretaría de Informática");

		String imgAuB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_IMG") + File.separatorChar + "psi.png"));
		attached = new AttachedJson();
		attached.setEmbedB64(imgAuB64);

		adminUnit.setInstitutionalImgToEmbed(attached);

		eExp.setAdminUnitCreate(adminUnit);

		PersonIdentityJson personIdentity = new PersonIdentityJson();
		personIdentity.setId(UUID.randomUUID().toString());
		personIdentity.setCode("ARG_DNI");
		personIdentity.setName("Documento Nacional de Identidad");
		personIdentity.setShortName("DNI");
		personIdentity.setNumber("27656133");

		PersonJson person = new PersonJson();
		person.setId(UUID.randomUUID().toString());
		person.setIdentities(new PersonIdentityJson[] { personIdentity });
		person.setNames(new String[] { "Diego", "Pablo" });
		person.setLastNames(new String[] { "Mansilla", "Cendra" });

		UserJson user = new UserJson();
		user.setId(UUID.randomUUID().toString());
		user.setName("dmansilla@unc.edu.ar");
		user.setPerson(person);

		eExp.setUserCreate(user);

		// EEXP ========================================================================

//		String pdfB64 = DatatypeConverter.printBase64Binary(SO.readBinary(GlobalProperties.get("PATH_INPUT_PDF") + File.separatorChar + "CONSTANCIA AFIP.pdf"));
//		attached = new AttachedJson();
//		attached.setEmbedB64(pdfB64);
//		String id = UUID.randomUUID().toString();
//		attached.setId(id);
//		attached.setName("CONSTANCIA AFIP.pdf");
//		attached.setUrl("https://cendra.unc.edu.ar/open?id=" + id);
		

//		eExp.setPdfToEmbed(attached);
		
		eExp.setCausative("Prosecretaría de Informática");	

		// ///////////////////////// INPUT ////////////////////////////////////////

		// System.out.println(eDog);

		// ///////////////////////// OUTPUT ////////////////////////////////////////

		EExpServices eExpServices = new EExpServices();
		String json = eExpServices.createEExp(eExp);

		// SO.parseB64AndcreateFileTemp("x", "pdf", new File("/home/java/Escritorio/"), b64)

		// System.out.println(json);

	}
	// public static void prueba() {

	// example();
	//
	// System.exit(0);
	//
	// AdminUnit au = new AdminUnit("Expediente");
	// au.setCode("76");
	// au.setShortName("PSI");
	// au.setName("Prosecretaría de Informática");
	// au.setPathImg("resources/images/psi.png");
	//
	// PersonIdentity personIdentity = new PersonIdentity("Expediente");
	// personIdentity.setCode("ARG_DNI");
	// personIdentity.setName("Documento Nacional de Identidad");
	// personIdentity.setShortName("DNI");
	// personIdentity.setNumber("27656133");
	//
	// Person person = new Person("Expediente");
	// person.setId(UUID.randomUUID().toString());
	// person.setIdentities(new PersonIdentity[] { personIdentity });
	// person.setNames(new String[] { "Diego", "Pablo" });
	// person.setLastNames(new String[] { "Mansilla", "Cendra" });
	//
	// User user = new User("Expediente");
	// user.setId(UUID.randomUUID().toString());
	// user.setName("dmansilla@unc.edu.ar");
	// user.setPerson(person);
	//
	// EexpBuilder eeBuilder = new EexpBuilder();
	//
	// String t = "1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890";
	//
	// try {
	// Eexp ee = new Eexp();
	// ee.setCudap("EEXP-UNC:666/2016");
	//
	// ee.setAdminUnit(au);
	// ee.setCausative("Diego Mansilla" + " " + t + t);
	//
	// ee.setSubject("Contrato de Locación de Servicios" + t + t + t + t + t);
	//
	// ee.setUser(user);
	//
	// for (int i = 0; i < 100; i++) {
	// ee.add("123456789012345678901234567890" + i, t + t);
	// }
	//
	// eeBuilder.build(ee);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// Eexp ee = new Eexp();
	// ee.setCudap("EEXP-UNC:777/2016");
	//
	// ee.setAdminUnit(au);
	// ee.setCausative("Prosecretaría de Informática");
	//
	// ee.setSubject("Contrato de locación de servicios de profesional independiente - Ing. Diego Mansilla");
	//
	// ee.setUser(user);
	//
	// ee.add("Tiempo resolución", "24 días hábiles");
	//
	// eeBuilder.build(ee);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// EdogBuilder edogBuilder = new EdogBuilder();
	//
	// Edog edog = new Edog();
	//
	// edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "CONSTANCIA AFIP.pdf");
	//
	// edog.setCudap("EDOG-UNC:111/2016");
	//
	// edog.setAdminUnit(au);
	// edog.setSubject("Constancia AFIP - Ing. Diego Mansilla");
	// // edog.setFooter("Haya de la Torre s/n - Pabellón Argentina - Ciudad Universitaria - Córdoba, Argentina");
	// edog.setPathImg("resources/images/unc.png");
	//
	// edog.setUser(user);
	//
	// edogBuilder.build(edog);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// EdogBuilder edogBuilder = new EdogBuilder();
	//
	// Edog edog = new Edog();
	//
	// edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "cv.pdf");
	//
	// edog.setCudap("EDOG-UNC:112/2016");
	//
	// edog.setAdminUnit(au);
	// edog.setSubject("CV - Ing. Diego Mansilla");
	// // edog.setFooter("Haya de la Torre s/n - Pabellón Argentina - Ciudad Universitaria - Córdoba, Argentina");
	// edog.setUser(user);
	//
	// edogBuilder.build(edog);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// EdogBuilder edogBuilder = new EdogBuilder();
	//
	// Edog edog = new Edog();
	//
	// edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "cv_diego_pablo_mansilla.pdf");
	//
	// edog.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	//
	// edog.setCudap("EDOG-UNC:113/2016");
	//
	// edog.setAdminUnit(au);
	// edog.setSubject("CV - Ing. Diego Mansilla");
	// // edog.setFooter("Haya de la Torre s/n - Pabellón Argentina - Ciudad Universitaria - Córdoba, Argentina");
	// edog.setUser(user);
	//
	// edogBuilder.build(edog);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// EdogBuilder edogBuilder = new EdogBuilder();
	//
	// Edog edog = new Edog();
	//
	// edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "cv_diego_pablo_mansilla.pdf");
	//
	// edog.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	//
	// edog.setCudap("EDOG-UNC:999000000/2016");
	//
	// edog.setAdminUnit(au);
	// edog.setSubject("CV - Ing. Diego Mansilla");
	// // edog.setFooter("Haya de la Torre s/n - Pabellón Argentina - Ciudad Universitaria - Córdoba, Argentina");
	// edog.setUser(user);
	//
	// edogBuilder.build(edog);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// try {
	// EexpPageBuilder eexpPageBuilder = new EexpPageBuilder();
	//
	// EexpPage eexpPage = new EexpPage();
	//
	// eexpPage.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	//
	// eexpPage.setPathSourceEd(PdfA3aBuilder.PATH_RESULT + "EDOG_UNC_113_2016.pdf");
	//
	// eexpPage.setCudap("EEXP-UNC:777/2016");
	//
	// eexpPage.setAdminUnit(au);
	// eexpPage.setSubject("DDDDDDDDDDDDDDDDDDDDDDDDDD");
	// // edog.setFooter("Haya de la Torre s/n - Pabellón Argentina - Ciudad Universitaria - Córdoba, Argentina");
	// eexpPage.setUser(user);
	//
	// eexpPageBuilder.build(eexpPage);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// // private static void example() {
	// // EexpBuilder eeBuilder = new EexpBuilder();
	// //
	// //// AdminUnit au = new AdminUnit("Expediente");
	// // au.setCode("76");
	// // au.setShortName("PSI");
	// // au.setName("Prosecretaría de Informática");
	// // au.setPathImg("resources/images/psi.png");
	// //
	// // PersonIdentity personIdentity = new PersonIdentity("Expediente");
	// // personIdentity.setCode("ARG_DNI");
	// // personIdentity.setName("Documento Nacional de Identidad");
	// // personIdentity.setShortName("DNI");
	// // personIdentity.setNumber("27656133");
	// //
	// // Person person = new Person("Expediente");
	// // person.setId(UUID.randomUUID().toString());
	// // person.setIdentities(new PersonIdentity[] { personIdentity });
	// // person.setNames(new String[] { "Diego", "Pablo" });
	// // person.setLastNames(new String[] { "Mansilla", "Cendra" });
	// //
	// // User user = new User("Expediente");
	// // user.setId(UUID.randomUUID().toString());
	// // user.setName("dmansilla@unc.edu.ar");
	// // user.setPerson(person);
	// //
	// // // ================= CARATULA ===================================================================
	// //
	// // Eexp ee = new Eexp();
	// //
	// // String eePathResult = null;
	// //
	// // try {
	// //
	// // ee.setCudap("EEXP-UNC:777/2016");
	// //
	// //// ee.setAdminUnit(au);
	// // ee.setCausative("Prosecretaría de Informática");
	// //
	// // ee.setSubject("Contrato de locación de servicios de profesional independiente - Ing. Diego Mansilla");
	// //
	// // ee.setUser(user);
	// //
	// // ee.add("Tiempo resolución", "24 días hábiles");
	// //
	// // eePathResult = eeBuilder.build(ee);
	// // } catch (Exception e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= EDOG CV ===================================================================
	// //
	// // Edog edog = new Edog();
	// //
	// // try {
	// // EdogBuilder edogBuilder = new EdogBuilder();
	// //
	// // edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "cv_diego_pablo_mansilla.pdf");
	// //
	// // edog.setCudap("EDOG-UNC:222/2016");
	// //
	// //// edog.setAdminUnit(au);
	// // edog.setSubject("CV - Ing. Diego Mansilla");
	// // edog.setPathImg("resources/images/unc.png");
	// //
	// // edog.setUser(user);
	// //
	// // edogBuilder.build(edog);
	// // } catch (Exception e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= FOLIO EDOG CV ===================================================================
	// //
	// // try {
	// // EexpPageBuilder eexpPageBuilder = new EexpPageBuilder();
	// //
	// // EexpPage eexpPage = new EexpPage();
	// //
	// // eexpPage.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	// //
	// // // eexpPage.setPathSourceEe("results/EEXP_UNC_777_20161459282799955.pdf");
	// // eexpPage.setPathSourceEe(eePathResult);
	// // eexpPage.setPathSourceEd(edog.getPathResultPdf());
	// //
	// // eexpPage.setAdminUnit(au);
	// // eexpPage.setUser(user);
	// //
	// // eePathResult = eexpPageBuilder.build(eexpPage);
	// //
	// // } catch (Exception e) {
	// //
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= EDOG AFIP ===================================================================
	// //
	// // edog = new Edog();
	// //
	// // try {
	// // EdogBuilder edogBuilder = new EdogBuilder();
	// //
	// // edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "CONSTANCIA AFIP.pdf");
	// //
	// // edog.setCudap("EDOG-UNC:111/2016");
	// //
	// //// edog.setAdminUnit(au);
	// // edog.setSubject("Constancia AFIP - Ing. Diego Mansilla");
	// // edog.setPathImg("resources/images/unc.png");
	// //
	// // edog.setUser(user);
	// //
	// // edogBuilder.build(edog);
	// // } catch (Exception e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= FOLIO EDOG AFIP ===================================================================
	// //
	// // try {
	// // EexpPageBuilder eexpPageBuilder = new EexpPageBuilder();
	// //
	// // EexpPage eexpPage = new EexpPage();
	// //
	// // eexpPage.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	// //
	// // eexpPage.setPathSourceEe(eePathResult);
	// // eexpPage.setPathSourceEd(edog.getPathResultPdf());
	// //
	// //// eexpPage.setAdminUnit(au);
	// // eexpPage.setUser(user);
	// //
	// // eePathResult = eexpPageBuilder.build(eexpPage);
	// //
	// // } catch (Exception e) {
	// //
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= EDOG CV2 ===================================================================
	// //
	// // edog = new Edog();
	// //
	// // try {
	// // EdogBuilder edogBuilder = new EdogBuilder();
	// //
	// // edog.setPathSource(PdfA3aBuilder.PATH_RESOURCES_PDF + "cv.pdf");
	// //
	// // edog.setCudap("EDOG-UNC:333/2016");
	// //
	// //// edog.setAdminUnit(au);
	// // edog.setSubject("CV - Ing. Diego Mansilla");
	// // edog.setPathImg("resources/images/unc.png");
	// //
	// // edog.setUser(user);
	// //
	// // edogBuilder.build(edog);
	// // } catch (Exception e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// //
	// // // ================= FOLIO EDOG CV ===================================================================
	// //
	// // try {
	// // EexpPageBuilder eexpPageBuilder = new EexpPageBuilder();
	// //
	// // EexpPage eexpPage = new EexpPage();
	// //
	// // eexpPage.setPathImg(PdfA3aBuilder.PATH_RESOURCES_IMG + "unc.png");
	// //
	// // eexpPage.setPathSourceEe(eePathResult);
	// // eexpPage.setPathSourceEd(edog.getPathResultPdf());
	// //
	// //// eexpPage.setAdminUnit(au);
	// // eexpPage.setUser(user);
	// //
	// // eePathResult = eexpPageBuilder.build(eexpPage);
	// //
	// // } catch (Exception e) {
	// //
	// // e.printStackTrace();
	// // }
	// //
	// // }
}
