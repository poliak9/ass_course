package ass.poliape2.dependencyinjection;

public class App {

	protected PrintLogger logger = new PrintLogger();
	
	public void run() {
		logger.log("Hello from app!");
	}
	
}
