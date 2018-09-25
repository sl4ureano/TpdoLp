package negocios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import auxiliares.InOut;

public class FuncoesConta {

	private static ArrayList<Conta> listaConta = new ArrayList<Conta>();

	public static void MenuPrincipal() throws IOException {
		int op;
		do {
			String opcoes = "Digite um dos Numeros abaixo:\n" + "\n1 - Cadastrar Conta\n" + "2 - Alterar Conta\n"
					+ "3 - Deletar Conta\n" + "4 - Deletar todas Contas\n" + "5 - Relatórios Gerenciais\n"
					+ "0 - Sair\n\n";
			op = InOut.InInt(opcoes);
			switch (op) {
			case 0:
				Salvar();
				InOut.OutMessage("O programa será Finalizado", "Atenção");
				break;
			case 1:
				CadastrarConta();
				break;
			case 2:
				AlterarConta();
				break;
			case 3:
				DeletarConta();
				break;
			case 4:
				ApagarContas();
				break;
			case 5:
				MenuRelatoriosGerencias();
				break;
			default:
				InOut.OutMessage("Opção Invalida!", "Erro!");
				break;
			}
		} while (op != 0);
	}

	public static void MenuRelatoriosGerencias() throws IOException {
		int op;
		do {
			String opcoes = "Digite um dos Numeros abaixo:\n" + "\n1 - Listar todas as Contas\n"
					+ "2 - Procurar Conta pelo Nome do Titular\n" + "3 - Procurar Conta por Saldo\n"
					+ "4 - Listar Contas com Saldo Negativo\n" + "5 -  Listar Contas com os Maiores Saldo\n"
					+ "0 - Voltar\n\n";
			op = InOut.InInt(opcoes);
			switch (op) {
			case 0:
				Salvar();
				break;
			case 1:
				ListaConta();
				break;
			case 2:
				ProcurarConta();
				break;
			case 3:
				ListaContaPorSaldo();
				break;
			case 4:
				ListaContasNegativas();
				break;
			case 5:
				Lista3MaioresContas();
				break;
			default:
				InOut.OutMessage("Opção Invalida!", "Erro!");
				break;
			}
		} while (op != 0);
	}

	public static void LerTxt() throws IOException {

		try (BufferedReader in = new BufferedReader(new FileReader(InOut.arquivo))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] pair = line.split(";");
				Conta conta = new Conta();
				conta.setNome(String.valueOf(pair[1]));
				conta.setSaldoCCorrente(Double.valueOf(pair[2]));
				conta.setSaldoCPoupanca(Double.valueOf(pair[3]));
				listaConta.add(conta);
			}
			in.close();
		} catch (Exception ex) {
			InOut.OutMessage("Error ao Importar contas do txt");
		}
	}

	public static void Salvar() throws IOException {
		try (FileWriter arq = new FileWriter(InOut.arquivo)) {

			PrintWriter gravaArq = new PrintWriter(arq);
			for (int i = 0; i < listaConta.size(); i++) {
				Conta conta = listaConta.get(i);
				gravaArq.printf(conta.getNumConta() + ";" + conta.getNome() + ";" + conta.getSaldoCCorrente() + ";"
						+ conta.getSaldoCPoupanca() + "\r\n");
			}
			gravaArq.close();
		} catch (Exception ex) {
			InOut.OutMessage("Error ao Salvar no Txt");
		}

	}

	public static void CadastrarConta() throws IOException {
		String nome;
		double cCorrente;
		double cPoupanca;

		nome = InOut.InString("Insira o Nome do Titular da Conta:");
		cCorrente = InOut.InDouble("Digite o Saldo da Conta-Corrente:");
		cPoupanca = InOut.InDouble("Digite o Saldo da Conta-Poupança:");

		do {

			if (verificaNome(nome)) {
				InOut.OutMessage("Já existe uma Conta com esse Nome!");
				nome = InOut.InString("Insira o Nome do Titular da Conta:");
			}

			if (nome.isEmpty()) {
				InOut.OutMessage("Campo Nome Não pode ficar vazio");
				nome = InOut.InString("Insira o Nome do Titular da Conta:");
			}

			if (cCorrente == 0) {
				InOut.OutMessage("Campo Conta-Corrente Não pode ficar vazio");
				cCorrente = InOut.InDouble("Digite o Saldo da Conta-Corrente:");
			}

			if (cPoupanca == 0) {
				InOut.OutMessage("Campo Conta-Poupança Não pode ficar vazio");
				cPoupanca = InOut.InDouble("Digite o Saldo da Conta-Corrente:");
			}

		} while (verificaNome(nome) || nome.isEmpty() || cCorrente <= 0 || cPoupanca <= 0);
		Conta conta = new Conta(nome, cCorrente, cPoupanca);
		listaConta.add(conta);
		Salvar();
	}

	public static boolean verificaNome(String nome) {
		boolean verifica = false;
		for (int x = 0; x < listaConta.size(); x++) {
			if (listaConta.get(x).getNome().equals(nome))
				verifica = true;
		}
		return verifica;
	}

	public static void ListaConta() throws IOException {
		if (listaConta.isEmpty()) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		String relatorio = "";
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			relatorio += "\nNúmero da Conta: " + conta.getNumConta() + "\nNome do Titular: " + conta.getNome()
					+ "\nSaldo Conta-Corrente: R$" + conta.getSaldoCCorrente() + "\nSaldo Conta-Poupança: R$"
					+ conta.getSaldoCPoupanca() + "\n----------------------------------------------------------\r";
		}
		InOut.OutMessage(relatorio);
	}

	public static void Lista3MaioresContas() throws IOException {
		if (listaConta.isEmpty()) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		Collections.sort(listaConta);
		String relatorio = "";
		for (int i = 0; i <= 2; i++) {
			Conta conta = listaConta.get(i);
			relatorio += "\nNúmero da Conta: " + conta.getNumConta() + "\nNome do Titular: " + conta.getNome()
					+ "\nSaldo Conta-Corrente: R$" + conta.getSaldoCCorrente() + "\nSaldo Conta-Poupança: R$"
					+ conta.getSaldoCPoupanca() + "\n----------------------------------------------------------\r";
		}
		InOut.OutMessage(relatorio);
	}

	public static void ListaContasNegativas() throws IOException {
		if (listaConta.isEmpty()) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		String relatorio = "";
		boolean verifica = false;
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);

			if (conta.getSaldoCCorrente() < 0) {
				relatorio += "\nNúmero da Conta: " + conta.getNumConta() + "\nNome do Titular: " + conta.getNome()
						+ "\nSaldo Conta-Corrente: R$" + conta.getSaldoCCorrente() + "\nSaldo Conta-Poupança: R$"
						+ conta.getSaldoCPoupanca() + "\n----------------------------------------------------------\r";
				verifica = true;
			} else {
				verifica = false;
			}

		}
		if (verifica != true) {
			InOut.OutMessage("Nenhuma Conta com Saldo Negativo Cadastrada");
		} else {
			InOut.OutMessage(relatorio);
		}

	}

	public static void ListaContaPorSaldo() throws IOException {
		if (listaConta.isEmpty()) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		double valorContaPesquisar = InOut.InDouble("Digite o valor que deseja Pesquisar:");
		String relatorio = "";
		boolean verifica = false;
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			if (conta.getSaldoCCorrente() >= valorContaPesquisar || conta.getSaldoCPoupanca() >= valorContaPesquisar) {
				relatorio += "\nNúmero da Conta: " + conta.getNumConta() + "\nNome do Titular: " + conta.getNome()
						+ "\nSaldo Conta-Corrente: R$" + conta.getSaldoCCorrente() + "\nSaldo Conta-Poupança: R$"
						+ conta.getSaldoCPoupanca() + "\n----------------------------------------------------------\r";
				verifica = true;
			} else {
				verifica = false;
			}
		}
		if (verifica != true) {
			InOut.OutMessage("Nenhuma Conta Nessa faixa de Saldo Cadastrada");
		} else {
			InOut.OutMessage(relatorio);
		}
	}

	public static void AlterarConta() throws IOException {
		if (listaConta.size() == 0) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		String nomeContaPesquisar = InOut.InString("Digite o Nome do Titular da Conta que deseja Editar:");
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			if (!verificaNome(nomeContaPesquisar)) {
				InOut.OutMessage("Nenhuma Conta Cadastrada com esse Nome!");
				break;
			}
			if (nomeContaPesquisar.equalsIgnoreCase(conta.getNome())) {
				String nomeNovo = InOut.InString("Digite o novo Nome do Titular:");
				double cCorrente = InOut.InDouble("Digite o Saldo da Conta-Corrente:");
				double cPoupanca = InOut.InDouble("Digite o Saldo da Conta-Poupança:");
				conta.setNome(nomeNovo);
				conta.setSaldoCCorrente(cCorrente);
				conta.setSaldoCPoupanca(cPoupanca);
				InOut.OutMessage("Conta alterada com sucesso");
				Salvar();
			}
		}

	}

	public static void ProcurarConta() {
		if (listaConta.size() == 0) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		String nomeContaPesquisar = InOut.InString("Digite o Nome do Titular da Conta que deseja Procurar:");
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			String relatorio = "";
			if (!verificaNome(nomeContaPesquisar)) {
				InOut.OutMessage("Nenhuma Conta Cadastrada com esse Nome!");
				break;
			}
			if (nomeContaPesquisar.equalsIgnoreCase(conta.getNome())) {
				relatorio += "\nNúmero da Conta: " + conta.getNumConta() + "\nNome do Titular: " + conta.getNome()
						+ "\nSaldo Conta-Corrente: R$" + conta.getSaldoCCorrente() + "\nSaldo Conta-Poupança: R$"
						+ conta.getSaldoCPoupanca() + "\n----------------------------------------------------------\r";
				InOut.OutMessage(relatorio);

			}
		}
	}

	public static void DeletarConta() throws IOException {
		if (listaConta.size() == 0) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		String nomeContaPesquisar = InOut.InString("Digite o Nome do Titular da Conta que deseja Deletar:");
		for (int i = 0; i < listaConta.size(); i++) {
			Conta conta = listaConta.get(i);
			if (!verificaNome(nomeContaPesquisar)) {
				InOut.OutMessage("Nenhuma Conta Cadastrada com esse Nome!");
				break;
			}
			if (nomeContaPesquisar.equalsIgnoreCase(conta.getNome())) {
				if (conta.getSaldoCCorrente() > 0 && conta.getSaldoCPoupanca() > 0) {
					InOut.OutMessage("Impossivel Deletar uma conta com Saldo Maior que 0!");
					break;
				} else {
					listaConta.remove(i);
					InOut.OutMessage("Conta deletada com Sucesso!");
					Salvar();
					break;
				}
			}
		}
	}

	public static void ApagarContas() throws IOException {
		if (listaConta.isEmpty()) {
			InOut.OutMessage("Nenhuma Conta Cadastrada");
			return;
		}
		listaConta.clear();
		Salvar();
		InOut.OutMessage("Todos as Contas foram Apagadas!");
	}

}
