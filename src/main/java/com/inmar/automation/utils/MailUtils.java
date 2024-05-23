package com.inmar.automation.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import com.sun.mail.imap.IMAPFolder;

public class MailUtils {
	private static MailUtils mailUtilsInstance = new MailUtils();

	public static MailUtils getInstance() {
		if (mailUtilsInstance == null) {
			mailUtilsInstance = new MailUtils();
		}
		return mailUtilsInstance;
	}

	
}