import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ViewCadVeiculo {
    private static ServiceVeiculo service = new ServiceVeiculo();
    static Scanner input = new Scanner(System.in);

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
   
    public static void cadastrarNovoVeiculo(){
        limparTela();
        Veiculo novoVeiculo = null;
        System.out.println("====== ADICIONANDO NOVO VEÍCULO ======");

        int tipoVeiculo;

        do {
            tipoVeiculo = inputNumericoOpcao("""
                Qual tipo de veículo?
                |(1) - Carro
                |(2) - Moto
                Digite a opção desejada:""");

            if (tipoVeiculo == 1){
                novoVeiculo = new Carro();
            }else if(tipoVeiculo == 2){
                novoVeiculo = new Moto();
            }else{
                System.out.println("Opção Inválida!");
            }
            
        } while (novoVeiculo == null);

        System.out.print("Informe a marca: ");
        String marca = input.nextLine();
        novoVeiculo.setMarca(marca);

        System.out.print("Informe o modelo: ");
        String modelo = input.nextLine();
        novoVeiculo.setModelo(modelo);

        int ano = inputNumericoAno("Informe o ano: ");
        novoVeiculo.setAno(ano);;

        System.out.print("Informe a placa: ");
        String placa = input.nextLine();
        novoVeiculo.setPlaca(placa);

        if(tipoVeiculo == 1){
            
            int numeroPortas;
        do {
            numeroPortas = inputNumericoNumeroPortas("Informe o nº de portas: ");
            if (numeroPortas <= 0) {
                System.out.println("O número de portas deve ser maior que 0!");
            }
        } while (numeroPortas <= 0);
           ((Carro) novoVeiculo).setNumeroPortas(numeroPortas);   
           
        }else if (tipoVeiculo == 2){
           
            int partidaEletricaOpcao = 0;

            inputNumericoOpcao("""
                É partida elétrica? 
                |(1) - sim
                |(2) - não
                Digite a opção desejada?""");
            if(partidaEletricaOpcao == 1){
                ((Moto) novoVeiculo).setPartidaEletrica(true);
            } else if(partidaEletricaOpcao == 2){
                ((Moto) novoVeiculo).setPartidaEletrica(false);
            }
            
        }

        try {
            service.save(novoVeiculo);
            System.out.println("Veículo adicionado com Sucesso!");
            
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO ");
            System.out.println(e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao Menu Inicial");
        input.nextLine();
    }

    public static void listarTodosVeiculosCadastrados(){
        limparTela();
        var veiculos = service.pesquisarTodosVeiculos();
        veiculos.sort(Comparator.comparing(Veiculo::getPlaca));

        System.out.println("======== LISTA DE VEÍCULOS =========");
        if(veiculos.isEmpty()){
            System.out.println("A lista está vazia.");}
        
        service.listarTodosVeiculosCadastrados();
        System.out.println("------------------------------------");
        
       
        
        aguardarEnter();
    }

    public static void pesquisarVeiculoPlaca() throws Exception{
        limparTela();
        System.out.println("====== PESQUISA DE VEÍCULOS POR PLACA ======");
        System.out.print("Insira a placa do veículo: ");
        String placa = input.nextLine();
        
        

        try {
            service.pesquisarVeiculoPlaca(placa);;
            System.out.println("Veículo encontrado com Sucesso!");
            
        } catch (Exception e) {
            System.out.println("Veículo não encontrado com a placa informada "  +  e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao Menu Inicial");
        input.nextLine();
        
        
        
           
            
    }
    public static void aguardarEnter() {
        System.out.print("Pressione Enter para continuar...");
        input.nextLine();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("ERRO. Valor informado deve ser um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }

    private static int inputNumericoAno(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.print("Digite um ano válido:");
            }
        } while (!entradaValida);
        return valor;
    }

    private static int inputNumericoOpcao(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.print("Digite um número dentro das Opções acima:");
            }
        } while (!entradaValida);
        return valor;
    }

    private static int inputNumericoNumeroPortas(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            
            String valorStr = input.nextLine();
            
            try {
                
                valor = Integer.parseInt(valorStr);
                entradaValida = true;

            } catch (Exception e) {
                System.out.print("Digite um número de portas válido (0 não é permitido):");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void removerVeiculoPlaca() {
        limparTela();
        System.out.println("====== EXCLUIR VEÍCULO POR PLACA ======");
        listarTodosVeiculosCadastrados();

        if (service.pesquisarTodosVeiculos().isEmpty()) {;
            System.out.println("Pressione Enter para voltar ao Menu Inicial");
            input.nextLine();
            return;  
        }
        System.out.print("Insira a placa do veículo que deseja REMOVER: ");
        String placa = input.nextLine();
        try {
            service.removerVeiculoPlaca(placa);
            System.out.println("Veiculo removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Pressione Enter para voltar ao Menu Inicial");
        input.nextLine();
    }

    public static void main(String[] args) throws Exception {
        String menu = """
                SISTEMA DE GERENCIAMENTO DE FROTAS
                Menu de Opções:
                1 - Cadastrar novo Veículo;
                2 - Listar todos Veículos cadastrados;
                3 - Pesquisar Veículo pela placa;
                4 - Remover Veículo;
                0 - Sair;
                Digite a opção desejada:""";
        int opcao;
        do {
            limparTela();
            opcao = inputNumericoOpcao(menu);
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    cadastrarNovoVeiculo();
                    break;
                case 2:
                    listarTodosVeiculosCadastrados();
                    break;
                case 3:
                    pesquisarVeiculoPlaca();
                    break;
                case 4:
                    removerVeiculoPlaca();
                    break;
                default:
                
                    System.out.println("Opção Inválida!!!");
                
                    aguardarEnter();
                    break;
            }
        } while (opcao != 0);
    }

}