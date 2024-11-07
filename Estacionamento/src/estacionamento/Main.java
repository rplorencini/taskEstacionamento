package estacionamento;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        estacionamento.cadastrarVaga(1, "pequeno");
        estacionamento.cadastrarVaga(2, "medio");
        estacionamento.cadastrarVaga(3, "grande");

        while (!sair) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Registrar entrada de veículo");
            System.out.println("2 - Registrar saída de veículo");
            System.out.println("3 - Mostrar vagas ocupadas");
            System.out.println("4 - Mostrar histórico de veículos");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo do veículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho do veículo (pequeno, medio, grande): ");
                    String tamanho = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa, modelo, tamanho);
                    estacionamento.registrarEntrada(veiculo);
                    break;

                case 2:
                    System.out.print("Placa do veículo: ");
                    String placaSaida = scanner.nextLine();
                    estacionamento.registrarSaida(placaSaida);
                    break;

                case 3:
                    estacionamento.mostrarVagasOcupadas();
                    break;

                case 4:
                    estacionamento.mostrarHistorico();
                    break;

                case 5:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
