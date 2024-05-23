
package com.inmar.automation.utils;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {
	static String key,data,from,to,password;
	static String[] AllToAddress;
	public void getData() throws IOException
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
	public void mail() throws IOException
	{
		getData(); 
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		//Sysmprop.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		//System.out.println(password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "false");

		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(to, password);
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

			//msg.setFrom(new InternetAddress("nagababu.qa507@gmail.com", "nagababu.qa507@gmail.com"));
			msg.setFrom(new InternetAddress(from));
			msg.setReplyTo(InternetAddress.parse(to, false));

			msg.setSubject("Test Report by Automation", "UTF-8");

			msg.setSentDate(new Date());
			System.out.println(12);

			//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nagababu.qa507@gmail.com"));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Find mail as report");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is image attachment
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir")
					+ "/target/cucumber-reports/extent-report/AutomationReport1.html";
			System.out.println(22);
			
			
			
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
			
			File f = new File(System.getProperty("user.dir")+"/target/cucumber-reports/extent-report");

	        // Populates the array with names of files and directories
	    	String[] myFiles = f.list();
	    	String path=System.getProperty("user.dir")+"/target/cucumber-reports/extent-report/";
	    	for(int i=0;i<myFiles.length;i++) {
	    		myFiles[i]=path+myFiles[i];
	    	}
	    	System.out.println(32);
	    	
	    	
	    	String zipFile = System.getProperty("user.dir")+"/target/cucumber-reports/extent-report.zip";
	    	ZipTheFolder zipfolder= new ZipTheFolder();
try {
	zipfolder.zip(myFiles, zipFile);
} catch (Exception ex) {
    // some errors occurred
    ex.printStackTrace();
  }
			// Second Attachement
			

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename2 = System.getProperty("user.dir") + "/target/cucumber-reports/extent-report.zip";
			System.out.println(42);
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
			System.out.println("EMail Sent Successfully with image!!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
/*	public void mail() throws IOException
	{
		getData();
		System.out.println(1);
		try
		{
			Properties Sysmprop=System.getProperties();
			
			Sysmprop.put("mail.smtp.starttls.enable", "true");
			Sysmprop.put("mail.smtp.starttls.required", "true");
			//Sysmprop.put("mail.smtp.host", "smtp.gmail.com");
			Sysmprop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			Sysmprop.put("mail.smtp.user", from);
			Sysmprop.put("mail.smtp.password", password);
			Sysmprop.put("mail.smtp.ssl.protocols", "TLSv1.2");
			System.out.println(password);
			Sysmprop.put("mail.smtp.port", "587");
			Sysmprop.put("mail.smtp.auth", "false");
			
			Session session = Session.getInstance(Sysmprop,null);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			InternetAddress[] ia=new InternetAddress[AllToAddress.length];
			for(int i=0;i<AllToAddress.length;i++)
			{
				ia[i]=new InternetAddress(AllToAddress[i]);
				
			}
			Date dt=new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			String strDate=formatter.format(dt);
			
			message.setSubject("Execution Reports On:"+strDate);
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("This is message body");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			System.out.println(2);
			File file2=new File("E:/ANG/Workspace/KycTest/target/cucumber-reports/extent-report/AutomationReport1.html");
			messageBodyPart=new MimeBodyPart();
			DataSource source=new FileDataSource(file2.getAbsolutePath());
			
			messageBodyPart.setDataHandler(new DataHandler(source));
			//messageBodyPart.setFileName("Report_"+strDate+".html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			
			
			Transport trans=session.getTransport("smtp");
			trans.connect("smtp.gmail.com",from,password);
			System.out.println(3);
			trans.sendMessage(message, message.getAllRecipients());
			//Transport.send(message);
			System.out.println(4);
			for(String address:AllToAddress)
			{
				System.out.println("Mail has been sent to :"+address);
			}
			trans.close();
		}
			
		
		catch(AddressException ae)
		{
			System.out.println("Address Exception :"+ae);
		}
		catch(MessagingException me)
		{
			System.out.println("Message Exception :"+me);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			

	}
*/
	public static void main(String[] args) {

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("ourmail", "****");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("ourmail"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("ourmail"));
            
                        // Add the subject link
			message.setSubject("Testing Subject");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("This is message body");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = "";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

}