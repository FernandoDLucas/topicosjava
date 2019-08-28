import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProcessCars {

	public static void main(String[] args) {

		Carro[] carros = { new Carro("Ford", "Corcel", 1985, 2000), new Carro("Ford", "Fiesta", 2015, 15000),
				new Carro("Ford", "Ranger", 2014, 75000), new Carro("Fiat", "Uno", 1999, 5000),
				new Carro("Fiat", "Palio", 2014, 10000), new Carro("Chevrolet", "Cobalt", 2017, 40000),
				new Carro("Chevrolet", "Onix", 2016, 30000), new Carro("Chevrolet", "Vectra", 2010, 23000),
				new Carro("Kia", "Sportage", 2014, 70000), new Carro("Toyota", "Corolla", 2018, 98000) };
		
		
		// obtém uma lista dos carros
		List<Carro> lista = Arrays.asList(carros);
				
		// exibe todos os carros
		System.out.println("Lista completa de empregados:");
		lista.stream().forEach(System.out::println);
		
		Predicate<Carro> ano00a15 = e -> (e.getAno() >= 2000 && e.getAno() <=2015);
		
		
		System.out.printf("%nCarros fabricados entre 2000 e 2015, ordenados por marca:%n");
		lista.stream()
			.filter(ano00a15)
			.sorted(Comparator.comparing(Carro::getAno))
			.forEach(System.out::println);
		
		System.out.printf("%nMedia dos preços de carros: %.2f%n ",
		lista.stream()
		.mapToDouble(Carro::getValor)
		.average()
		.getAsDouble());
				
		
		
		// Agrupa por Marca
				System.out.printf("%nCarros por Marca:%n");
				Map<String, List<Carro>> agrupadosPorMarca =
						lista.stream().collect(Collectors.groupingBy(Carro::getMarca));
				// System.out.println(agrupadosPorDepartamento);
				agrupadosPorMarca.forEach(
						(Marca, carroMarca) -> 
						{
							System.out.println(Marca);
							carroMarca.forEach(
									carro -> System.out.printf("     %s%n", carro));
						}
				);
		
		
				
				// Numero de carro por marca
				System.out.printf("%nNúmero de carros por marca: %n");
				Map<String, Long> numerocarrosPorMarca =
						lista.stream()
							.collect(Collectors.groupingBy(Carro::getMarca, 
									TreeMap::new, Collectors.counting()));
		
				numerocarrosPorMarca.forEach(
						(marca, quantidade) -> System.out.printf(
								"%s possui %d carro(s)%n", marca, quantidade));
				
	}
	

}
