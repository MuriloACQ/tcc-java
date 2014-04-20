package drive;

public class Handler extends Thread {
	
	private String path;
	
	public Handler(String path){
		this.path = path+":/";
	}
	
	public void run() {
		System.out.println("Handling "+ path);
	}
}
