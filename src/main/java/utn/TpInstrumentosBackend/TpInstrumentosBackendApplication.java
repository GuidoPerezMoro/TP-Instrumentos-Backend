package utn.TpInstrumentosBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpInstrumentosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpInstrumentosBackendApplication.class, args);
		System.out.println("\n--------------------- Estoy activo en el main ---------------------");
		System.out.println("Visualizacion en H2: http://localhost:8080/h2-console/");
	}

}
