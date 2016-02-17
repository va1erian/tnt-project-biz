package com.app5.tnt.utils;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Class allowing to easily send HTML5 mail using the google account
 * tnt.project.polytech@gmail.com (can be changed by changing constants)
 * 
 * @author Robin Delgado
 *
 */
public class MailUtility {

	// Static template path
	public final static String INSCRIPTION_MAIL = "inscription";

	// Sender's information
	private final static String EXPEDITION_ADDRESS = "tnt.project.polytech@gmail.com";
	private final String USERNAME = "tnt.project.polytech@gmail.com";
	private final String PASSWORD = "supertnt";

	private TemplateEngine templateEngine;
	private Session mailingSession;

	/**
	 * Default constructor, initialize thymeleaf template engine and the mailing
	 * session.
	 * 
	 * There is no parameter because we use constants instead
	 */
	public MailUtility() {
		// Initialisation du template engine
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode("HTML5");
		resolver.setSuffix(".html");
		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(resolver);

		// Initialisation de la session
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		mailingSession = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(USERNAME,
								PASSWORD);
					}
				});
	}

	/**
	 * Uses thymeleaf framework to get an html email from a html template/
	 * 
	 * If the hashmap keys does not match with any parameter, it is ignored
	 * 
	 * @param templatePath
	 *            Path of the template file
	 * @param parametersValues
	 *            Map containing serval tuple < name of property, value of
	 *            property > (used to create the HTML mail)
	 * @return html email as string
	 */
	private String getHtmlMailContent(String templatePath,
			Map<String, String> parametersValues) {

		Context context = new Context(Locale.FRANCE);

		for (Entry<String, String> item : parametersValues.entrySet()) {
			// System.out.println("Parameter: " + item.getKey() + " Value: "
			// + item.getValue());
			context.setVariable(item.getKey(), item.getValue());
		}

		return templateEngine.process(templatePath, context);
	}

	/**
	 * Send an HTML5 email build with the specified template and parametersValue
	 * 
	 * @param templatePath
	 *            Path of the template file without extension
	 * @param destinator
	 *            Destination email address
	 * @param parametersValues
	 *            Map containing serval tuple < name of property, value of
	 *            property > (used to create the HTML mail) Keys that does not
	 *            exists within the template will be ignored. Template's field
	 *            will remain blank if not specified in the map
	 * @throws Exception
	 */
	public void sendEmail(String templatePath, String destinator,
			Map<String, String> parametersValues) throws RuntimeException {
		String html = getHtmlMailContent(templatePath, parametersValues);

		if (html == null || html.isEmpty())
			throw new RuntimeException(
					"Problèmes rencontrés lors de la génération du mail");

		// Create a default MimeMessage object.
		Message message = new MimeMessage(mailingSession);

		try {
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(EXPEDITION_ADDRESS));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinator));

			// Set Subject: header field
			message.setSubject("TntProject");

			// Set content
			message.setContent(html, "text/html");

			// Send message
			Transport.send(message);
		} catch (Exception e) {
			throw new RuntimeException("Failed to send mail", e);
		}
	}
}
