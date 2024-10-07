import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    private List<Veiculo> frota = new ArrayList<>();
    private final int ANO_NAO_PERMITIDO = 0;
    public Veiculo save(Veiculo veiculo) throws Exception {
    
        
        if(veiculo.getMarca() == null || veiculo.getMarca().isEmpty() )
        throw new Exception("Campo Marca não pode ser em branco!");
        
        if(veiculo.getModelo() == null || veiculo.getModelo().isEmpty())
        throw new Exception("Não é permitido cadastrar um veículo sem o Modelo!");
        
        if (veiculo.getAno() == ANO_NAO_PERMITIDO){
            throw new Exception("Não é permitido cadastrar um veículo com o ano igual a 0!");
        }

        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty())
        throw new Exception("Não é permitido cadastrar veículo sem a Placa!");
        
        
        for (Veiculo veiculoFrota : frota) {
            if (veiculoFrota.getPlaca().equalsIgnoreCase(veiculo.getPlaca()))
                throw new Exception("Já existe veículo cadastrado com essa placa");
        }

        if (veiculo instanceof Carro) {
        Carro carro = (Carro) veiculo;
        if (carro.getNumeroPortas() <= 0) {
            throw new Exception("Não é permitido cadastrar um carro com número de portas igual a 0!");
        }
    }
        
        frota.add(veiculo);
        return veiculo;
    }

    public List<Veiculo> findAll() {
        return frota;
    }

    public Veiculo pesquisarVeiculoPlaca(String placa) throws Exception {
    
        Veiculo veiculoRet = null;

        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoRet = veiculo;
                System.out.println("Veículo" + veiculo.toString());
                break;
            }
        }
        if (veiculoRet == null)
            throw new Exception("| Veículo não encontrado com a placa informada");
        return veiculoRet;
    }

    public List<Veiculo> pesquisarTodosVeiculos(){

        return this.frota;
    }
   
    
    
    
    public void listarTodosVeiculosCadastrados( ){
        int contador = 1;

        for (Veiculo veiculo : frota) {
            System.out.println("Veículo " + contador + ": " + veiculo.toString());
            contador++; 
        }


    }

    public void removerVeiculoPlaca(String placa) throws Exception{
        boolean veiculoRemovido = false;
        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                veiculoRemovido = true;
                frota.remove(veiculo);
                break;
            }
        }
        if (!veiculoRemovido){
            throw new Exception("Veículo não encontrado com a placa informada");}
    }



    }

    