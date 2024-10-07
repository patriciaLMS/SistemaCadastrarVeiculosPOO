public class Moto extends Veiculo{
    private boolean partidaEletrica;

    public String isPartidaEletrica() {
        return partidaEletrica ? "Sim" : "Não"; 
    }

   
       
        
    

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;

        
    }

    


    @Override //annotations
    public String toString() {
       String descricao = super.toString();
        return descricao  + " - Partida Elétrica: " + this.isPartidaEletrica();
    }
}
