package res;

import java.net.URL;

public class ResourceLoader {
	
	public static URL getResourceURL(String res) {
		return ResourceLoader.class.getClassLoader().getResource("res/" + res);
	}
	
}
