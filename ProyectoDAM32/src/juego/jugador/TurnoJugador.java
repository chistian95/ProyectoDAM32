package juego.jugador;

public class TurnoJugador extends Thread {

	private Jugador jugador;
	
	public TurnoJugador(Jugador jugador) {
		this.jugador = jugador;
		start();
	}

	public void run() {
		System.out.println("Turno de "+jugador.getNacion());
		jugador.hacerTurno();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
