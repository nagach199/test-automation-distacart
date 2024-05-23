package com.inmar.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSending {
	static String key,data,from,to,password;
	static String[] AllToAddress;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getData(); 
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		//Sysmprop.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "false");

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nagababu.qa507@gmail.com", "ulscgryweinfiycw");
			}
		});
		// compose message
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {

			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("nagababu.qa507@gmail.com", "nagababu.qa507@gmail.com"));

			msg.setReplyTo(InternetAddress.parse("nagababu.qa507@gmail.com", false));

			msg.setSubject("Test Report by Automation", "UTF-8");

			msg.setSentDate(new Date());
			System.out.println(12);

			//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ourmail"));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Automation report");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir")
					+ "/Reports/extent-report/AutomationReport.html";
			
			
			
			
			Date dt=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			String strDate=formatter.format(dt);
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			//messageBodyPart.setFileName(filename);
			messageBodyPart.setFileName("Report_"+strDate+".html");
			// Trick is to add the content-id header here
			messageBodyPart.setHeader("Content-ID", "image_id");
			
			// zip the cucumber html report--
			
			File f = new File(System.getProperty("user.dir")+"/Reports/extent-report");

	        // Populates the array with names of files and directories
	    	String[] myFiles = f.list();
	    	String path=System.getProperty("user.dir")+"/Reports/extent-report/";
	    	for(int i=0;i<myFiles.length;i++) {
	    		myFiles[i]=path+myFiles[i];
	    	}
	    	
	    	
	    	
	    	//String zipFile = System.getProperty("user.dir")+"/target/cucumber-reports.zip";
	    	String zipFile = System.getProperty("user.dir")+"/Reports/extent-report.zip";
	    	ZipTheFolder zipfolder= new ZipTheFolder();
	    	
	    	
try {
	zipfolder.zip(myFiles, zipFile);
	
} catch (Exception ex) {
    // some errors occurred
    ex.printStackTrace();
  }
			// Second Attachement
			

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename2 = System.getProperty("user.dir") + "/Reports/extent-report.zip";
			
			try {
				messageBodyPart2.attachFile(filename2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart2);

			// third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h1>Attached is the zip</h1>", "text/html");
			multipart.addBodyPart(messageBodyPart);
			System.out.println(52);
			// Set the multipart message to the email message
			msg.setContent(multipart);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			
			// Send message
			Transport trans=session.getTransport("smtp");
			trans.connect("smtp.gmail.com",from,password);
			System.out.println(3);
			trans.sendMessage(msg, msg.getAllRecipients());
			//Transport.send(message);
			System.out.println(4);
			for(String address:AllToAddress)
			{
				System.out.println("Mail has been sent to :"+address);
			}
			trans.close();
			System.out.println("EMail Sent Successfully with zip!!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	
	
	
	
	public static void getData() throws IOException
	{
		File pro=new File("Input/credentials.properties");
		FileInputStream input=new FileInputStream(pro);
		Properties props = new Properties();
		props.load(input);
		Enumeration value=props.keys();
		while(value.hasMoreElements()){
			key=(String) value.nextElement();
			data=props.getProperty(key);
			if(key.equals("to"))
			{
				to=data;
			}
			if(key.equals("from"))
			{
				from=data;
			}
			if(key.equals("password"))
			{
				password=data;
			}
		}
		AllToAddress=to.split(",");
	}
}
