package ass.poliape2.dependencyinjection;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DIMain {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		App app = new App();
		app.run();
	}
	
}
