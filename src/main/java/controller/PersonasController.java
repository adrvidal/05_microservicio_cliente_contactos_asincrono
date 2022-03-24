package controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Persona;
import servicio.AccesoService;

@RestController
public class PersonasController {

	@Autowired
	AccesoService accesoService;

	@GetMapping(value = "/personas/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaNueva(@PathVariable("nombre") String nombre, @PathVariable("email") String email,
			@PathVariable("edad") int edad) throws InterruptedException, ExecutionException {
		Persona persona = new Persona(nombre, email, edad);
		CompletableFuture<List<Persona>> res = accesoService.llamadaServicio(persona);
		for (int i = 0; i < 50; i++) {
			System.out.println("Tarea del controlador:"+i+"\n");
			Thread.sleep(50);

		}
		return res.get();
	}

}
