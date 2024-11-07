package estacionamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Vaga> vagas = new ArrayList<>();
    private List<Registro> historico = new ArrayList<>();

    public void cadastrarVaga(int numero, String tamanho) {
        vagas.add(new Vaga(numero, tamanho));
    }

    public void registrarEntrada(Veiculo veiculo) {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.isCompativel(veiculo.getTamanho())) {
                vaga.ocupar(veiculo);
                veiculo.setHoraEntrada(LocalDateTime.now());
                System.out.println("Veículo " + veiculo.getPlaca() + " estacionado na vaga " + vaga.getNumero());
                return;
            }
        }
        System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
    }

    public void registrarSaida(String placa) {
        for (Vaga vaga : vagas) {
            Veiculo veiculo = vaga.getVeiculo();
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                veiculo.setHoraSaida(LocalDateTime.now());
                double valor = calcularValor(veiculo);
                veiculo.setValorPago(valor);
                
                historico.add(new Registro(veiculo.getPlaca(), veiculo.getHoraEntrada(), veiculo.getHoraSaida(), valor));
                
                vaga.liberar();
                System.out.println("Veículo " + veiculo.getPlaca() + " saiu. Valor a pagar: R$ " + valor);
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private double calcularValor(Veiculo veiculo) {
        Duration duracao = Duration.between(veiculo.getHoraEntrada(), veiculo.getHoraSaida());
        long horas = duracao.toHours();
        if (horas == 0) horas = 1; // arredonda para pelo menos 1 hora se o tempo for menor que uma hora completa

        if (horas <= 1) {
            return 5.0;
        } else if (horas <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    public void mostrarVagasOcupadas() {
        System.out.println("Vagas Ocupadas:");
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel()) {
                System.out.println("Vaga: " + vaga.getNumero() + ", Tamanho: " + vaga.getTamanho() + ", Placa: " + vaga.getVeiculo().getPlaca());
            }
        }
    }

    public void mostrarHistorico() {
        System.out.println("Histórico de Veículos:");
        for (Registro reg : historico) {
            System.out.println("Placa: " + reg.getPlaca() + ", Entrada: " + reg.getEntrada() + ", Saída: " + reg.getSaida() + ", Valor Pago: R$ " + reg.getValorPago());
        }
    }
}

