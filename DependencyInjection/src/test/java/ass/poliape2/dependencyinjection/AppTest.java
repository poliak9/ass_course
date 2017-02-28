package ass.poliape2.dependencyinjection;

import org.testng.Assert;
import org.testng.annotations.Test;

class LoggerMock implements Logger {
	protected String lastMessage;

	public void log(String message) {
		this.lastMessage = message;
	}
	
	public String getLastMessage() {
		return lastMessage;
	}
}

public class AppTest {

	@Test
	public void run() {
		// prepare mock
		LoggerMock mock = new LoggerMock();

		App app = new App();
		
		// TODO: inject LoggerMock into app
		
		app.run();
		Assert.assertEquals(mock.getLastMessage(), "Hello world!");
	}
}
