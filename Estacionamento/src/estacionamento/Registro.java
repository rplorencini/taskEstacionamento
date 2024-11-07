package estacionamento;

import java.time.LocalDateTime;

public class Registro {
    private String placa;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public Registro(String placa, LocalDateTime entrada, LocalDateTime saida, double valorPago) {
        this.placa = placa;
        this.entrada = entrada;
        this.saida = saida;
        this.valorPago = valorPago;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public long getDuracaoHoras() {
        return java.time.Duration.between(entrada, saida).toHours();
    }
}
