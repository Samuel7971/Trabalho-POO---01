
package trabalho1_poo.trabalho_poo;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @author Edith Soares Matos - RGM: 23031361
 * @author Samuel Rodrigues de Souza - RGM 22977996
 */
public class RecursoDidaticoMain {
    
    String nome = "", email = "", especialidade = "", nomeU = "", emailU = "", login = "", senha = "", titulo = "", nivelEnsino = "", 
           formato = "", diciplina = "", dataAtualizacao = "", descricao = "", termoPortugues = "", termoIngles = "", dataCriacao = "";
    int codigo = 0, opcao = 0, cont = 0, novoC = 0;
        
    //Manipulação das Classes
    ArrayList<Recurso> listaRecurso = new ArrayList();
    ArrayList<Autor> autor = new ArrayList();
    ArrayList<Usuario> usuario = new ArrayList();
    private Usuario user;
    private Autor at;
    private PalavraChave pc;
    private Historico hist;
    private Autor autorVet[];
    private Historico histVet[];
    private PalavraChave palavraVet[];
    private Recurso recurso; 

    public static void main(String[] args) {
       RecursoDidaticoMain principal = new RecursoDidaticoMain();
    }
    
    public RecursoDidaticoMain(){
        criandoUsuarioAut();
        while(novoC != 2){
            limpar();
            telaPrincipal();
        }
        
    }
    
    public void telaPrincipal(){ // Tela inicial
       
        String[] str = new String[]{"Cadastra recurso","Consultar Recurso", "Sair"};
        opcao = JOptionPane.showOptionDialog(null, "Escolha a Opção","Tela Principal" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, 0);
        
        switch(opcao){
            case 0:
                boolean valida = validarUsuario();
                if (valida == true) {
                    cadastraRecurso();
                    dadosDoAutor();
                    dadosTermoPortuguesIngles();
                    dadosHistorico();
                    criandoRecurso();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuário não esta cadastrado! Click OK para se cadastrar!");
                    dadosDoUsuario();
                    telaPrincipal();
                }
                break;
                
            case 1:
                consultaRecurso();
                break; 
                
            case 2:
                JOptionPane.showMessageDialog(null, "ATÉ LOGO!");
                novoC = 2;
                break;
        }
        
    }
    
    public void limpar(){
        titulo = "";
        nivelEnsino = "";
        formato = "";
        diciplina = "";
        dataCriacao = "";
    }
      
    public void cadastraRecurso(){ // Cadastro inicio recurso
        cont++;
        Random aleatorio = new Random();
        System.out.println(" ***** Cadastro de Recurso ***** ");
        codigo = aleatorio.nextInt(2000) + 1000;
        titulo = JOptionPane.showInputDialog("Digite o Título: ");
        nivelEnsino = JOptionPane.showInputDialog("Digite o nivel de ensino: ");
        formato = JOptionPane.showInputDialog("Digite o formato: ");
        diciplina = JOptionPane.showInputDialog("Digite a dicíplina: ");
        dataCriacao = JOptionPane.showInputDialog("Digite a data da criação: ");
    }
    
    public void dadosDoAutor(){  //Dados autor
        nome = JOptionPane.showInputDialog("Digite o nome do autor: ");
        email = JOptionPane.showInputDialog("Digite o e-mail do autor: ");
        especialidade = JOptionPane.showInputDialog("Digite a especialidade: ");
        at = new Autor(nome, email, especialidade);
        autor.add(at);
    }
    
    public void dadosDoUsuario(){  // Dados usuário
        nomeU = JOptionPane.showInputDialog("Digite o nome do usuário: ");
        emailU = JOptionPane.showInputDialog("Digite do e-mail do usuário: ");
        login = JOptionPane.showInputDialog("Cadastrar login do usuário: ");
        senha = JOptionPane.showInputDialog("Cadastrar senha do usuário: ");
        user = new Usuario(nomeU, emailU, login, senha);
        usuario.add((Usuario)user);  
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }
    
    public boolean validarUsuario(){  // Validar usuário
        String loginE, senhaE;
        loginE = JOptionPane.showInputDialog("Entre com o login: user padrão: sa");
        senhaE = JOptionPane.showInputDialog("Entre com a senha: senha padrão: sa");

        for(Usuario user : usuario){
            if (loginE.equalsIgnoreCase(user.getLogin()) && senhaE.equalsIgnoreCase(user.getSenha())) {
                 return true;
            }     
        }   
        return false;
    }
    
    public void dadosTermoPortuguesIngles(){  //Dados termos 
        termoPortugues = JOptionPane.showInputDialog("Digite o termo em Português: ");
        termoIngles = JOptionPane.showInputDialog("Digite o termo em Inglês: ");
        pc = new PalavraChave(termoPortugues, termoIngles);
    }
    
    
    public void dadosHistorico(){  // Dados histórico
        dataAtualizacao = JOptionPane.showInputDialog("Data de atualização: ");
        descricao = JOptionPane.showInputDialog("Descirção: ");
        hist = new Historico((Usuario)user, dataAtualizacao, descricao);
    }
    
    public void criandoRecurso(){      // Criando recurso ,mostrando resumo
        autorVet = new Autor[cont];
        palavraVet = new PalavraChave[cont];
        histVet = new Historico[cont];
        for (int i = 0; i < autorVet.length; i++) {
            autorVet[i] = at;
            palavraVet[i] = pc;
            histVet[i] = hist;
        }
        recurso = new Recurso(codigo, titulo, nivelEnsino, formato, diciplina, dataCriacao, autorVet, palavraVet, histVet);
        listaRecurso.add(recurso);
        JOptionPane.showMessageDialog(null, "Resumo: " + recurso.resumo());
    }
     
    public void consultaRecurso(){ // Método consultar recurso
        String dataConsulta;
        dataConsulta = JOptionPane.showInputDialog("Entre com a data de criação para consulta: ");
        if(!autor.isEmpty()){
            for(Recurso re : listaRecurso){
                if(dataConsulta.equalsIgnoreCase(re.getDataCriacao())){
                    JOptionPane.showMessageDialog(null," Resculatado da consulta: " + re.resumo());
                    break;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Não há recurso cadastrado!");
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Método para inserir dados de Usuário automatica
    public void criandoUsuarioAut(){
        //Cadastro de Usuário
        nomeU = "Samuel Rodrigues de Souza";
        emailU = "samuel@gmail.com";
        login = "sa";
        senha = "sa";
        user = new Usuario(nomeU, emailU, login, senha);
        usuario.add((Usuario)user);
        
    }
}
