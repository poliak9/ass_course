package ass.poliape2.dependencyinjection;

public class PrintLogger implements Logger {

	public void log(String message) {
		System.out.println(message);
	}
	
}
