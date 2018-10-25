package com.citrix.elearning.candidatemerge.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.util.IOUtils;

import com.sendgrid.Attachments;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class MailService {

	public static Response sendMail(String fromEmail, String subjectMail, String toMail, String filePath)
			throws IOException {
		Email from = new Email(fromEmail);

		Email to = new Email(toMail);
		Content content = new Content("text/plain", PropertyUtil.getProperty("content"));
		Mail mail = new Mail(from, subjectMail, to, content);

		Attachments attachments = new Attachments();
		Base64 x = new Base64();
		File file = new File(filePath);
		byte[] fileData = null;
		try {
			fileData = IOUtils.toByteArray(new FileInputStream(file));
		} catch (IOException ex) {
		}
		String excelDataString = x.encodeAsString(fileData);
		attachments.setContent(excelDataString);
		attachments.setDisposition("attachment");
		attachments.setFilename("candidateResult.xlsx");
		mail.addAttachments(attachments);
		SendGrid sg = new SendGrid(PropertyUtil.getProperty("sendGridAPIkey"));
		Request request = new Request();
		Response response = null;
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
		return response;

	}

}
