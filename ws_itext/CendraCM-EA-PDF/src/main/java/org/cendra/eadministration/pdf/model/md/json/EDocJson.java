package org.cendra.eadministration.pdf.model.md.json;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.cendra.eadministration.pdf.util.UtilJson;

public class EDocJson {

	private String id;
	private String[] objImplements;
	private String objName;
	private String objTitle;
	private String objDescription;
	private String[] objTags;
	private String createDate;
	private UserJson userCreate;
	private AdminUnitJson adminUnitCreate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getObjImplements() {
		return objImplements;
	}

	public void setObjImplements(String[] objImplements) {
		this.objImplements = objImplements;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjTitle() {
		return objTitle;
	}

	public void setObjTitle(String objTitle) {
		this.objTitle = objTitle;
	}

	public String getObjDescription() {
		return objDescription;
	}

	public void setObjDescription(String objDescription) {
		this.objDescription = objDescription;
	}

	public String[] getObjTags() {
		return objTags;
	}

	public void setObjTags(String[] objTags) {
		this.objTags = objTags;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public UserJson getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(UserJson userCreate) {
		this.userCreate = userCreate;
	}

	public AdminUnitJson getAdminUnitCreate() {
		return adminUnitCreate;
	}

	public void setAdminUnitCreate(AdminUnitJson adminUnitCreate) {
		this.adminUnitCreate = adminUnitCreate;
	}

	public String toString() {
		String json = "";

		json += UtilJson.buildAttJson("id", id) + ",";
		json += UtilJson.buildAttJson("objImplements", objImplements) + ",";
		json += UtilJson.buildAttJson("objName", objName) + ",";
		json += UtilJson.buildAttJson("objTitle", objTitle) + ",";
		json += UtilJson.buildAttJson("objDescription", objDescription) + ",";
		json += UtilJson.buildAttJson("objTags", objTags) + ",";
		json += UtilJson.buildAttJson("createDate", createDate) + ",";
		json += "\"userCreate\":" + userCreate + ",";
		json += "\"adminUnitCreate\":" + adminUnitCreate + ",";

		return json;
	}

	

}
