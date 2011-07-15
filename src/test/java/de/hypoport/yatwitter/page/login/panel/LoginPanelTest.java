package de.hypoport.yatwitter.page.login.panel;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import de.hypoport.AbstractTest;
import de.hypoport.yatwitter.TwitterMockApplication;

public class LoginPanelTest extends AbstractTest {

	@Test
	public void testLoginPanel() {
		WicketTester tester = new WicketTester(new TwitterMockApplication());
		tester.startPage(LoginTestPage.class);
		FormTester formTester = tester.newFormTester("loginPanel:form");
		formTester.setValue("name", "Steffen");
		formTester.setValue("password", "123456");
		formTester.submit();

		tester.assertErrorMessages(new String[] { "Der Benutzer existiert noch nicht in der Datenbank." });
	}

	@Test
	public void testRenderLoginPanel() {
		WicketTester tester = new WicketTester();
		tester.startPage(LoginTestPage.class);
		tester.assertRenderedPage(LoginTestPage.class);
	}
}
