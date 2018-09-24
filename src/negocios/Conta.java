package negocios;

public class Conta {

	private String nome;
	private int numConta;
	private double saldoCCorrente;
	private double saldoCPoupanca;
	private static int contadorConta = 0;

	public Conta() {
		contadorConta++;
		this.numConta = contadorConta;
	}

	public Conta(String nome, double saldoCCorrente, double saldoCPoupanca) {
		contadorConta++;
		this.numConta = contadorConta;
		this.nome = nome;
		this.saldoCCorrente = saldoCCorrente;
		this.saldoCPoupanca = saldoCPoupanca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	public double getSaldoCCorrente() {
		return saldoCCorrente;
	}

	public void setSaldoCCorrente(double saldoCCorrente) {
		this.saldoCCorrente = saldoCCorrente;
	}

	public double getSaldoCPoupanca() {
		return saldoCPoupanca;
	}

	public void setSaldoCPoupanca(double saldoCPoupanca) {
		this.saldoCPoupanca = saldoCPoupanca;
	}

}
