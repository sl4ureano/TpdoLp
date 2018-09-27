package auxiliares;

import javax.swing.JOptionPane;

public class InOut {
        //Declarando a constante com o nome do arquivo que será usado para ler e gravar todas as informacoes do sistema
	public static final String arquivo = "contas.txt";
        //Convertendo String para Int vindas do JOptionPane e retornando
	public static int InInt(String titulo) {
		try {
			int in = Integer.parseInt(JOptionPane.showInputDialog(titulo));
			return in;
		} catch (Exception e) {
			return 0;
		}
	}
        //Convertendo String para Float vindas do JOptionPane e retornando
	public static float InFloat(String titulo) {
		float in = Float.parseFloat(JOptionPane.showInputDialog(titulo));
		return in;
	}
        //Convertendo String para Double vindas do JOptionPane
	public static double InDouble(String titulo) {
		try {
			double in = Double.parseDouble(JOptionPane.showInputDialog(titulo));
			return in;
		} catch (Exception e) {
			return 0;
		}

	}

	public static String InString(String titulo) {
		String in = JOptionPane.showInputDialog(titulo);
		return in;
	}

	public static void OutMessage(String msg, String titulo) {
		JOptionPane.showMessageDialog(null, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void OutMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
