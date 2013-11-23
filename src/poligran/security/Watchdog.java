package poligran.security;

/**
 * Clase encargada de vigilar si un proceso se 
 * excedio en el tiempo de ejecucion estimado
 * @author amzubieta
 */
public class Watchdog implements Runnable {

	//Hilo responsable de observar el proceso en ejecucion
	private Thread observador;
	//Flag para indicar si el proceso se finalizo dentro del tiempo limite
	private boolean finalizoConExito;
	
	//Proceso en ejecucion, el que debe ser observado
	private Thread observado;
	//Tiempo limite del proceso
	private long tiempoLimite = Long.MIN_VALUE;
	
	
	/*******************************
	 * Constructores
	 *******************************/
	
	public Watchdog(final Thread observado) {
		this(observado, 10000L);
	}
	
	public Watchdog(final Thread observado, final long tiempoLimite) {
		if(tiempoLimite < 1){
			throw new IllegalArgumentException("El tiempo limite de espera del watchdog debe ser un numero de milisegundos positivo");
		}
		
		this.observado = observado;
		this.tiempoLimite = tiempoLimite;
		this.start();
	}

	/**
	 * Inicia la vigilancia del proceso
	 */
	public synchronized void start() {
		finalizoConExito = false;
		observador = new Thread(this, "WatchDog starting for: " + observado);
		observador.setDaemon(true);
		observador.start();
	}
	
	/**
	 * Valida que el tiempo de ejecucion del proceso, este 
	 * dentro del tiempo esperado.
	 */
	public synchronized void run() {
		final long tiempoFinal = System.currentTimeMillis() + tiempoLimite;
		long tiempoActual;
		while (!finalizoConExito && tiempoFinal > (tiempoActual = System.currentTimeMillis())) {
			try {
				wait(tiempoFinal - tiempoActual);
			} catch (InterruptedException e) {
				//La validacion sobre si finalizo con exito, se hace justo despues de salir del ciclo 
			}
		}
		if (!finalizoConExito) {
			System.out.println("Deteniendo el hilo: " + observado);
			Thread.currentThread().interrupt();
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Finaliza la vigilancia del proceso
	 */
	public synchronized void stop() {
		finalizoConExito = true;
		notifyAll();
		System.out.println("Finalizo con exito la ejecucion de: " + observado);
	}
	
	public String toString(){
		return observador != null ? observador.getName() : String.valueOf(this);
	}
}