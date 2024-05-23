package com.inmar.automation.configs;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataConstants {
	 
	public static final String TITLE_ = "";
	public static final String TITLE__ = ""; 
	
	public static final String[] Excepted_drp_options={"Abraham Natural Foods","Amazon","American Outdoor Products","Ample Hills (Schmitt Ind.)","Blue Apron","Bozzuto's","C&S Wholesale Grocers","Christmas Tree Shops","Coastal Pacific Food","Davidson",
			"Dekalb Farmers Market","Diana's Bananas","Dot Foods","DPI - West","Food Lion (Hannaford)","Fresh Direct","General Trading Company Inc.","Grab the Gold","HelloFresh","HelloFresh Canada",
			"Holly Hill","iHerb, Inc.","KeHe","Lipari Foods","Marley Spoon","Merridian Distributors","Misfits Market","Nassau Candy","Peyton - Kroger","Royal Foods International",
			"UNFI","US Foods","Valu Merchandisers Company /AWG","Vitacost","Wakefern"};
	
	//Remitance Tab
	public static final String[] Excepted_drp_options_itemizationstatus={"Payment Received","Exists in ERP","Mismatch with ERP","Itemized","Ready to Itemize"};
	
	public static final String[] Excepted_header_column_deductionbycustomer={"Customers","Deduction type","Total Deductions","Nov","Dec","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct"};
	
	public static final String[] Excepted_assign_approvers={"Unassigned","IB User","Inmar User","Unit Test","DeepakShravan Gorantla","Nagababu Cherukumalli","Neeraja VenkatRanga"};
	
	
	
	
	
	public static String getDownloadsPath() {
		String resourcesPath = System.getenv("RESOURCES_PATH");
		if (resourcesPath == null || resourcesPath.trim().isEmpty() || !Files.exists(Paths.get(resourcesPath))) {
			return System.getProperty("user.dir").replace("\\", "/") + "/downloads";
		}
		return Paths.get(resourcesPath, "downloads").toString();
		
	}
	public static String getDownloadsPath_linux() { 
		String resourcesPath = System.getenv("RESOURCES_PATH");
		if (resourcesPath == null || resourcesPath.trim().isEmpty() || !Files.exists(Paths.get(resourcesPath))) {
			return System.getProperty("user.dir").replace("\\", "/") + "/linuxdriver";
		}
		return Paths.get(resourcesPath, "downloads").toString();
		
	}
	/**
	 * Get the path to the document resources based on availability of shared storage (In the case of k8s & grid)
	 * @return {String} the absolute path to the documents folder
	 */
	private static String getDocumentPath() {
		String resourcesPath = System.getenv("RESOURCES_PATH");
		if (resourcesPath == null || resourcesPath.trim().isEmpty() || !Files.exists(Paths.get(resourcesPath))) {
			Path currentPath = Paths.get(System.getProperty("user.dir"));
			return Paths.get(currentPath.toString(), "src", "main", "resources", "AutomationCsv").toString();
		}
		return Paths.get(resourcesPath, "cloud", "canopy", "documents").toString();
	}
	/**
	 * Get the path to a engine document
	 * @param file {String} The filename
	 * @return {String} the absolute path to the document file
	 */
	public static String getDocumentPath(String file) { 
		return Paths.get(getDocumentPath(), "DMP", file).toString();
	}
}
 