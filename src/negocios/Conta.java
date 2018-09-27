package negocios;

public class Conta implements Comparable<Conta>{
        // declaração dos atributos
	private String nome;
	private int numConta;
	private double saldoCCorrente;
	private double saldoCPoupanca;	 
	private static int contadorConta = 0;
        //construtor com um sistema de auto_increment, assim nenhuma conta terá o mesmo número
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
        //esse metodo é obrigatório para o uso do Collection.Sort usado para verificar as 3MaioresContas na classe FuncoesConta
	//Nele eu estou ordenando os itens da Arraylist de acordo com o saldoCCorrente do maior para o menor
	@Override
	public int compareTo(Conta outraConta) {
		 if (this.saldoCCorrente > outraConta.getSaldoCCorrente()) {
	          return -1;
	     }
	     if (this.saldoCCorrente < outraConta.getSaldoCCorrente()) {
	          return 1;
	     }
	     return 0;
	}

}
