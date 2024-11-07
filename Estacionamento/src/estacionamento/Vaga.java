package estacionamento;

public class Vaga {
    private int numero;
    private String tamanho; // "pequeno", "medio" ou "grande"
    private boolean disponivel;
    private Veiculo veiculo; // Veículo estacionado nesta vaga

    public Vaga(int numero, String tamanho) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.disponivel = true;
        this.veiculo = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void ocupar(Veiculo veiculo) {
        this.disponivel = false;
        this.veiculo = veiculo;
    }

    public void liberar() {
        this.disponivel = true;
        this.veiculo = null;
    }

    public boolean isCompativel(String tamanhoVeiculo) {
        if (tamanho.equals("grande")) return true; // Vagas grandes são compatíveis com todos
        if (tamanho.equals("medio")) return !tamanhoVeiculo.equals("grande"); // Vagas médias suportam veículos médios e pequenos
        return tamanho.equals(tamanhoVeiculo); // Vagas pequenas só para veículos pequenos
    }
}
