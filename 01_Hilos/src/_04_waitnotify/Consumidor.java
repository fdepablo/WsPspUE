package _04_waitnotify;

public class Consumidor implements Runnable{

	private Buffer buffer;
	private String nombre;
		
	public Consumidor(Buffer buffer, String nombre) {
		super();
		this.buffer = buffer;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		for(int i=1;i<=10;i++) {
			String m = buffer.getMensaje();
			System.out.println("-" + nombre + " he consumido el mensaje: " + m);
		}
		
	}
	
}
