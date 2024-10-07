public class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private String placa;


    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    @Override
    public String toString() {


       String descricao =  " Tipo: " + this.getClass().getSimpleName() + " - Marca: " + this.getMarca() + " - " + this.getModelo() + " - Ano: " + this.getAno() + " - Placa: " + this.getPlaca();
        return descricao;
    }

}