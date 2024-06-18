package utn.TpInstrumentosBackend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utn.TpInstrumentosBackend.Enum.Rol;
import utn.TpInstrumentosBackend.entities.*;
import utn.TpInstrumentosBackend.repositories.CategoriaRepository;
import utn.TpInstrumentosBackend.repositories.InstrumentoRepository;
import utn.TpInstrumentosBackend.repositories.PedidoRepository;
import utn.TpInstrumentosBackend.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TpInstrumentosBackendApplication {
	@Autowired
	InstrumentoRepository instrumentoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpInstrumentosBackendApplication.class, args);
		System.out.println("\n--------------------- Estoy activo en el main ---------------------");
		System.out.println("Visualizacion en H2: http://localhost:8080/h2-console/");
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			{
				Categoria categoria1 = Categoria.builder()
						.categoria("Cuerda")
						.build();

				Categoria categoria2 = Categoria.builder()
						.categoria("Viento")
						.build();
				Categoria categoria3 = Categoria.builder()
						.categoria("Percusión")
						.build();

				Categoria categoria4 = Categoria.builder()
						.categoria("Teclado")
						.build();

				Categoria categoria5 = Categoria.builder()
						.categoria("Electrónico")
						.build();

				categoriaRepository.save(categoria1);
				categoriaRepository.save(categoria2);
				categoriaRepository.save(categoria3);
				categoriaRepository.save(categoria4);
				categoriaRepository.save(categoria5);

				Instrumento instrumento1 = Instrumento.builder()
						.instrumento("Mandolina Instrumento Musical Stagg Sunburst")
						//.active(false)
						.marca("Stagg")
						.modelo("M20")
						.imagen("https://www.heavenimagenes.com/heavencommerce/c98a269a-2842-4da0-a6e5-e0e155d29966/images/v2/STAGG/1808291259027891_03_medium.jpg")
						.precio(2450f)
						.costoEnvio("G")
						.cantidadVendida(28)
						.descripcion("Estas viendo una excelente mandolina de la marca Stagg.")
						.categoria(categoria1)
						.build();
				instrumentoRepository.save(instrumento1);

				Instrumento instrumento2 = Instrumento.builder()
						.instrumento("Pandereta Pandero Instrumento Musical ")
						.marca("DyM ventas")
						.modelo("32 sonajas")
						.imagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5w1VD4sisWr3JQII2_p_a1cuhkFXzGNPpdmrB5s5-7Q&s")
						.precio(325f)
						.costoEnvio("150")
						.cantidadVendida(10)
						.descripcion(" Pandereta - 32 sonajas metálicas. Más de 8 años vendiendo con 100 % de calificaciones POSITIVAS y clientes satisfechos !! ")
						.categoria(categoria3)
						.build();
				instrumentoRepository.save(instrumento2);

				Instrumento instrumento3 = Instrumento.builder()
						.instrumento("Triangulo Musical 24 Cm Percusion")
						.marca("LBP")
						.modelo("24")
						.imagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT76yg8JWkcQBshWoVnJZwVmUGXq-5gf5o3MHwrlqgUMw&s")
						.precio(260f)
						.costoEnvio("250")
						.cantidadVendida(31)
						.descripcion("Triangulo Musical de 24 Centímetros De Acero.")
						.categoria(categoria3)
						.build();
				instrumentoRepository.save(instrumento3);

				Instrumento instrumento4 = Instrumento.builder()
						.instrumento("Bar Chimes Lp Cortina Musical 72 Barras")
						.marca("FM")
						.modelo("LATIN")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_670997-MLA31575604825_072019-O.webp")
						.precio(2250f)
						.costoEnvio("G")
						.cantidadVendida(2)
						.descripcion("BARCHIME CORTINA MUSICAL DE 25 BARRAS LATIN CUSTOM. Emitimos factura A y B")
						.categoria(categoria3)
						.build();
				instrumentoRepository.save(instrumento4);

				Instrumento instrumento5 = Instrumento.builder()
						.instrumento("Shekeres. Instrumento. Música. Artesanía.")
						.marca("Azalea Artesanías")
						.modelo("Cuentas de madera")
						.imagen("https://galeriaartesanalpr.com/cdn/shop/files/SHEKERE800LOGO1_b57b606d-b046-49c2-83cf-1819de952cec_600x.jpg?v=1693183399")
						.precio(850f)
						.costoEnvio("300")
						.categoria(categoria3)
						.cantidadVendida(5)
						.descripcion("Las calabazas utilizadas para nuestras artesanías son sembradas y cosechadas por nosotros, quienes seleccionamos el mejor fruto para garantizar la calidad del producto y ofrecerle algo creativo y original.")
						.build();
				instrumentoRepository.save(instrumento5);

				Instrumento instrumento6 = Instrumento.builder()
						.instrumento("Antiguo Piano Aleman Con Candelabros.")
						.marca("Neumeyer")
						.modelo("Stratus")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_741625-MLA25468412771_032017-O.webp")
						.precio(17000f)
						.costoEnvio("2000")
						.cantidadVendida(0)
						.categoria(categoria4)
						.descripcion("Buen dia! Sale a la venta este Piano Alemán Neumeyer con candelabros incluidos. Tiene una talla muy bonita en la madera. Una pieza de calidad.")
						.build();

				instrumentoRepository.save(instrumento6);

				Instrumento instrumento7 = Instrumento.builder()
						.instrumento("Guitarra Ukelele Infantil Grande 60cm")
						.marca("GUITARRA")
						.modelo("UKELELE")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_602949-MLA32297056646_092019-O.webp")
						.precio(500f)
						.costoEnvio("G")
						.cantidadVendida(5)
						.categoria(categoria1)
						.descripcion("Material: Plástico similar a madera. 4 Cuerdas longitud: 60cm, el mejor regalo para usted, su familia y amigos, adecuado para 3-18 años de edad.")
						.build();
				instrumentoRepository.save(instrumento7);

				Instrumento instrumento8 = Instrumento.builder()
						.instrumento("Teclado Organo Electronico Musical Instrumento 54 Teclas")
						.marca("GADNIC")
						.modelo("T01")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_851863-MLU71029857717_082023-O.webp")
						.precio(2250f)
						.costoEnvio("G")
						.cantidadVendida(105)
						.categoria(categoria5)
						.descripcion("Organo Electrónico GADNIC T01. Display de Led. 54 Teclas. 100 Timbres / 100 Ritmos. 4 1/2 octavas. 8 Percusiones. 8 Canciones de muestra. Grabación y reproducción. Entrada para Micrófono.")
						.build();
				instrumentoRepository.save(instrumento8);

				Instrumento instrumento9 = Instrumento.builder()
						.instrumento("Instrumentos De Percusión Niños Set Musical Con Estuche")
						.marca("KNIGHT")
						.modelo("LB17")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_773874-MLA52856983256_122022-O.webp")
						.precio(2700f)
						.costoEnvio("300")
						.categoria(categoria3)
						.cantidadVendida(15)
						.descripcion("Estas viendo un excelente y completísimo set de percusión para niños con estuche rígido, equipado con los instrumentos más divertidos! ")
						.build();
				instrumentoRepository.save(instrumento9);

				Instrumento instrumento10 = Instrumento.builder()
						.instrumento("Batería Musical Infantil Juguete Niño 9 Piezas Palillos")
						.marca("Bateria")
						.modelo("Infantil")
						.imagen("https://http2.mlstatic.com/D_NQ_NP_643960-MLA27368744727_052018-O.webp")
						.precio(850)
						.costoEnvio("250")
						.cantidadVendida(210)
						.categoria(categoria3)
						.descripcion("DESCRIPCIÓN: DE 1 A 3 AÑOS. EL SET INCLUYE 5 TAMBORES, PALILLOS Y EL PLATILLO TAL CUAL LAS FOTOS. SONIDOS REALISTAS Y FÁCIL DE MONTAR. MEDIDAS: 40X20X46 CM")
						.build();
				instrumentoRepository.save(instrumento10);

				// Creación de usuarios
				if (usuarioRepository.count() == 0) {
					Usuario developer = new Usuario();
					developer.setUsername("developer");
					developer.setPassword("1234");
					developer.setRol(Rol.DEVELOPER);
					usuarioRepository.save(developer);
					System.out.println("Usuario developer creado");

					Usuario admin = new Usuario();
					admin.setUsername("admin");
					admin.setPassword("1234");
					admin.setRol(Rol.ADMIN);
					usuarioRepository.save(admin);
					System.out.println("Usuario admin creado");

					Usuario operador = new Usuario();
					operador.setUsername("operador");
					operador.setPassword("1234");
					operador.setRol(Rol.OPERADOR);
					usuarioRepository.save(operador);
					System.out.println("Usuario operador creado");

					Usuario cliente = new Usuario();
					cliente.setUsername("cliente");
					cliente.setPassword("1234");
					cliente.setRol(Rol.CLIENTE);
					usuarioRepository.save(cliente);
					System.out.println("Usuario cliente creado");


					//Creación de pedidos
					// Obtenemos los instrumentos previamente guardados
					List<Instrumento> instrumentos = instrumentoRepository.findAll();

					// Lista de pedidos a insertar
					List<Pedido> pedidos = new ArrayList<>();

					// Generamos 10 pedidos con diversas fechas, cantidades y totalPedido calculado
					pedidos.add(createPedido(instrumentos, LocalDate.of(2023, 10, 15), new int[]{1, 2, 3, 4}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2023, 12, 22), new int[]{5, 6, 7, 8}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2023, 12, 25), new int[]{9, 10, 1, 2}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 1, 9), new int[]{3, 4, 5, 6}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 2, 18), new int[]{7, 8, 9, 10}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 3, 30), new int[]{1, 3, 5, 7}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 3, 14), new int[]{2, 4, 6, 8}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 6, 5), new int[]{10, 9, 8, 7}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 6, 17), new int[]{6, 5, 4, 3}));
					pedidos.add(createPedido(instrumentos, LocalDate.of(2024, 6, 25), new int[]{2, 3, 4, 5}));

					// Guardamos los pedidos en la base de datos
					pedidoRepository.saveAll(pedidos);
				}
			};
		};
	}
	// Método para crear un pedido con una fecha dada y cantidades para cada instrumento
	private Pedido createPedido(List<Instrumento> instrumentos, LocalDate fecha, int[] cantidades) {
		List<DetallePedido> detallesPedido = new ArrayList<>();
		double totalPedido = 0;

		for (int i = 0; i < cantidades.length; i++) {
			Instrumento instrumento = instrumentos.get(i % instrumentos.size());
			int cantidad = cantidades[i];
			double precio = instrumento.getPrecio();

			DetallePedido detalle = DetallePedido.builder()
					.instrumento(instrumento)
					.cantidad(cantidad)
					.build();
			detallesPedido.add(detalle);

			totalPedido += precio * cantidad;
		}

		return Pedido.builder()
				.fecha(fecha)
				.totalPedido(totalPedido)
				.detallesPedido(detallesPedido)
				.build();
	}
}
