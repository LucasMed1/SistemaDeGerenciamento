import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private ArrayList<Pedido> pedidos;
    private int proximoNumero;

    public Sistema() {
        pedidos = new ArrayList<>();
        proximoNumero = 1;
    }

    public void registrarPedido(Scanner sc) {
        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();

        Pedido pedido = new Pedido(proximoNumero++, nomeCliente);

        boolean adicionando = true;
        while (adicionando) {
            System.out.print("Nome do item: ");
            String nomeItem = sc.nextLine();

            System.out.print("Preço do item: ");
            double precoItem = Double.parseDouble(sc.nextLine());

            pedido.adicionarItem(new Item(nomeItem, precoItem));

            System.out.print("Adicionar outro item? (s/n): ");
            String resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("s")) {
                adicionando = false;
            }
        }

        pedidos.add(pedido);
        System.out.println("Pedido registrado com sucesso!");
        pedido.exibirResumo();
    }

    public void removerPedido(Scanner sc) {
        System.out.print("Número do pedido a remover: ");
        int numero = Integer.parseInt(sc.nextLine());

        boolean removido = pedidos.removeIf(p -> p.getNumero() == numero);

        if (removido) {
            System.out.println("Pedido removido com sucesso.");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
        } else {
            for (Pedido p : pedidos) {
                p.exibirResumo();
            }
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Remover Pedido");
            System.out.println("3. Listar Pedidos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    registrarPedido(sc);
                    break;
                case "2":
                    removerPedido(sc);
                    break;
                case "3":
                    listarPedidos();
                    break;
                case "4":
                    rodando = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
