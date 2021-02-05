package com.cep.cep;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cep.cep.model.Cep;

public class CepApplication {

	public static void main(String[] args) throws URISyntaxException {
		
		System.out.println("Digite o CEP:");
		Scanner input = new Scanner(System.in);
		String cepInformado = input.nextLine();

		String url = "http://viacep.com.br/ws/"+cepInformado+"/json";
		URI uri = new URI(url);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Cep> responseEntity= restTemplate.getForEntity(uri, Cep.class);

		if (responseEntity.getBody().getLocalidade() == null){
			System.out.println("CEP não encontrado!!");
		}else{
			System.out.println("Cep: "+ responseEntity.getBody().getCep());
			System.out.println("Cidade: "+ responseEntity.getBody().getLocalidade());
			System.out.println("Bairro: "+ responseEntity.getBody().getBairro());
			System.out.println("Endereco: "+ responseEntity.getBody().getLogradouro());
			System.out.println("Estado: "+ responseEntity.getBody().getUf());
			
		}
	}

}
