package testes;

import java.io.IOException;
import java.util.Scanner;

import negocios.*;
import auxiliares.*;

public class TestaApp {

    public static void main(String[] args) throws IOException {
        //Verifica Senha do admin para mostrar menu com funcionalidades
        String senha = InOut.InString("Digite a Senha de Acesso:");
        if (FuncoesConta.verificaSenha(senha)) {
            //Inicio a aplicação obtendo informaçoes do Txt e jogando em um arraylist a partir do metodo LerTxt()
            FuncoesConta.LerTxt();
            //Logo após inicio o Menu Principal chamando o metodo MenuPrincipal()
            FuncoesConta.MenuPrincipal();
        } else {
            InOut.OutMessage("Acesso Negado!");
        }

    }

}
