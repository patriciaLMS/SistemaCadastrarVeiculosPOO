public class Carro extends Veiculo{
    private int numeroPortas;

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
        
    }
    @Override //annotations
    public String toString() {
       String descricao = super.toString();
    return descricao  + " - Número de Portas: " + this.getNumeroPortas();
    }
}
