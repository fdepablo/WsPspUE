package _03_sincronizacion;
public class Obrero extends Thread{
	
	private int inicioLadrillo,finLadrillo;
	private String nombre;
	private Silla silla;
	
	//1, 100
	public Obrero(int inicioLadrillo, int finLadrillo, String nombre) {
		this.inicioLadrillo = inicioLadrillo;
		this.finLadrillo = finLadrillo;
		this.nombre = nombre;
	}
	
	public void ponerLadrillos() {
		for(int i = inicioLadrillo;i<=finLadrillo;i++) {
			System.out.println("Obrero " + nombre + " esta Poniendo el ladrillo " + i);
			//cada 10 segundos un obrero descansa
			//y se sienta en la silla durante 10 segundo
			if(i % 10 == 0) {
				silla.descansar(this);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//Esto es excatamente "igual" a un main de java
	@Override
	public void run() {
		ponerLadrillos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Silla getSilla() {
		return silla;
	}

	public void setSilla(Silla silla) {
		this.silla = silla;
	}
	
	
}
