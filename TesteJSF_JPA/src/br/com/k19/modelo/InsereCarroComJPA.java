package br.com.k19.modelo;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InsereCarroComJPA {

	public static void main(String[] args){
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("k19db-pu");
		
		EntityManager manager = factory.createEntityManager();
		
		Carro novoCarro = new Carro();
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Digite o nome da marca: ");
		novoCarro.setMarca(entrada.nextLine());
		
		System.out.println("Digite o nome de carro: ");
		novoCarro.setModelo(entrada.nextLine());
		
		entrada.close();
		
		manager.persist(novoCarro);
		
		manager.getTransaction().begin();
		manager.getTransaction().commit();
		
		System.out.println("Carro cadastrado com id: " + novoCarro.getId());
		
		manager.close();
		factory.close();
	}
}
