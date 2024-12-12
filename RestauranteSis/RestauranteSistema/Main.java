import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Garcom> garcons = new ArrayList<>();
    private static final List<Prato> pratos = new ArrayList<>();
    private static final List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        preencherDadosFicticios();

        System.out.println("Bem-vindo ao Sistema de Restaurante!");
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Garçom");
            System.out.println("3. Cadastrar Prato");
            System.out.println("4. Fazer Pedido");
            System.out.println("5. Listar Pedidos");
            System.out.println("6. Relatórios");
            System.out.println("7. Exportar Relatórios para Arquivo");
            System.out.println("8. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarGarcom();
                    break;
                case 3:
                    cadastrarPrato();
                    break;
                case 4:
                    realizarPedido();
                    break;
                case 5:
                    listarPedidos();
                    break;
                case 6:
                    mostrarRelatorios();
                    break;
                case 7:
                    exportarRelatoriosParaArquivo();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void preencherDadosFicticios() {
        clientes.addAll(List.of(
                new Cliente("João Silva", "12345678900", "(11) 98765-4321", "joao@gmail.com", "Rua A, 123"),
                new Cliente("Maria Oliveira", "23456789001", "(21) 97654-3210", "maria@gmail.com", "Rua B, 456"),
                new Cliente("Carlos Pereira", "34567890102", "(31) 96543-2109", "carlos@gmail.com", "Rua C, 789"),
                new Cliente("Ana Souza", "45678901203", "(41) 95432-1098", "ana@gmail.com", "Rua D, 101"),
                new Cliente("Pedro Rocha", "56789012304", "(51) 94321-0987", "pedro@gmail.com", "Rua E, 202")
        ));

        garcons.addAll(List.of(
                new Garcom("Lucas Mendes", "G01", "(61) 93210-9876", "lucas@gmail.com"),
                new Garcom("Paula Nunes", "G02", "(71) 92109-8765", "paula@gmail.com"),
                new Garcom("Tiago Costa", "G03", "(81) 91098-7654", "tiago@gmail.com")
        ));

        pratos.addAll(List.of(
                new Prato("Feijoada", "Feijoada completa com arroz, farofa e couve", 35.50),
                new Prato("Lasanha", "Lasanha à bolonhesa gratinada", 29.90),
                new Prato("Salada Caesar", "Salada Caesar com frango grelhado", 22.00),
                new Prato("Picanha", "Picanha grelhada com acompanhamento", 55.00)
        ));

        pedidos.addAll(List.of(
                new Pedido(clientes.get(0), garcons.get(0), List.of(pratos.get(0), pratos.get(1)), new Date(), "em preparo"),
                new Pedido(clientes.get(1), garcons.get(1), List.of(pratos.get(2)), new Date(), "finalizado"),
                new Pedido(clientes.get(2), garcons.get(2), List.of(pratos.get(1), pratos.get(3)), new Date(), "em preparo"),
                new Pedido(clientes.get(3), garcons.get(0), List.of(pratos.get(0)), new Date(), "finalizado")
        ));
    }

    private static void cadastrarCliente() {
        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        if (clientes.stream().anyMatch(c -> c.getCpf().equals(cpf))) {
            System.out.println("Erro: CPF já cadastrado!");
            return;
        }
        System.out.println("Digite o telefone do cliente Use o formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx.:");
        String telefone = scanner.nextLine();
        if (!telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            System.out.println("Erro: Telefone inválido! Use o formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx.");
            return;
        }
        System.out.println("Digite o email do cliente:");
        String email = scanner.nextLine();
        if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Erro: Email inválido!");
            return;
        }
        System.out.println("Digite o endereço do cliente:");
        String endereco = scanner.nextLine();
        clientes.add(new Cliente(nome, cpf, telefone, email, endereco));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarGarcom() {
        System.out.println("Digite o nome do garçom:");
        String nome = scanner.nextLine();
        System.out.println("Digite o código do garçom:");
        String codigo = scanner.nextLine();
        if (garcons.stream().anyMatch(g -> g.getCodigo().equals(codigo))) {
            System.out.println("Erro: Código já cadastrado!");
            return;
        }
        System.out.println("Digite o telefone do garçom:");
        String telefone = scanner.nextLine();
        if (!telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            System.out.println("Erro: Telefone inválido! Use o formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx.");
            return;
        }
        System.out.println("Digite o email do garçom:");
        String email = scanner.nextLine();
        if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            System.out.println("Erro: Email inválido!");
            return;
        }
        garcons.add(new Garcom(nome, codigo, telefone, email));
        System.out.println("Garçom cadastrado com sucesso!");
    }

    private static void cadastrarPrato() {
        System.out.println("Digite o nome do prato:");
        String nome = scanner.nextLine();
        System.out.println("Digite a descrição do prato:");
        String descricao = scanner.nextLine();
        System.out.println("Digite o preço do prato:");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        pratos.add(new Prato(nome, descricao, preco));
        System.out.println("Prato cadastrado com sucesso!");
    }

    private static void realizarPedido() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        Cliente cliente = clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
        if (cliente == null) {
            System.out.println("Erro: Cliente não encontrado!");
            return;
        }
        System.out.println("Digite o código do garçom:");
        String codigo = scanner.nextLine();
        Garcom garcom = garcons.stream().filter(g -> g.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (garcom == null) {
            System.out.println("Erro: Garçom não encontrado!");
            return;
        }
        List<Prato> pratosPedido = new ArrayList<>();
        System.out.println("Digite o nome dos pratos (digite 'fim' para terminar):");
        while (true) {
            String nomePrato = scanner.nextLine();
            if (nomePrato.equalsIgnoreCase("fim")) {
                break;
            }
            Prato prato = pratos.stream().filter(p -> p.getNome().equalsIgnoreCase(nomePrato)).findFirst().orElse(null);
            if (prato != null) {
                pratosPedido.add(prato);
            } else {
                System.out.println("Prato não encontrado! Tente novamente.");
            }
        }
        if (pratosPedido.isEmpty()) {
            System.out.println("Erro: Pedido deve conter pelo menos um prato!");
            return;
        }
        System.out.println("Digite o status do pedido (em preparo/finalizado):");
        String status = scanner.nextLine();
        pedidos.add(new Pedido(cliente, garcom, pratosPedido, new Date(), status));
        System.out.println("Pedido realizado com sucesso!");
    }

    private static void listarPedidos() {
        System.out.println("Lista de Pedidos:");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
        } else {
            pedidos.forEach(System.out::println);
        }
    }

    private static void mostrarRelatorios() {
        System.out.println("\nEscolha um relatório:");
        System.out.println("1. Clientes cadastrados");
        System.out.println("2. Garçons disponíveis");
        System.out.println("3. Pratos do cardápio");
        System.out.println("4. Pedidos realizados");
        System.out.println("5. Pedidos por garçom específico");
        System.out.println("6. Pedidos realizados nos últimos 7 dias");
        System.out.println("7. Quantidade de pratos vendidos por nome");
        System.out.println("8. Quantidade de pedidos por status");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Clientes cadastrados:");
                clientes.forEach(System.out::println);
                break;
            case 2:
                System.out.println("Garçons disponíveis:");
                garcons.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Pratos do cardápio:");
                pratos.forEach(System.out::println);
                break;
            case 4:
                System.out.println("Pedidos realizados:");
                pedidos.forEach(System.out::println);
                break;
            case 5:
                System.out.println("Digite o código do garçom:");
                String codigo = scanner.nextLine();
                pedidos.stream().filter(p -> p.getGarcom().getCodigo().equals(codigo)).forEach(System.out::println);
                break;
            case 6:
                System.out.println("Pedidos realizados nos últimos 7 dias:");
                Date seteDiasAtras = new Date(System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000);
                pedidos.stream().filter(p -> p.getData().after(seteDiasAtras)).forEach(System.out::println);
                break;
            case 7:
                System.out.println("Quantidade de pratos vendidos por nome:");
                pratos.forEach(prato -> {
                    long count = pedidos.stream()
                            .flatMap(p -> p.getPratos().stream())
                            .filter(p -> p.getNome().equals(prato.getNome()))
                            .count();
                    System.out.println(prato.getNome() + ": " + count);
                });
                break;
            case 8:
                long emPreparo = pedidos.stream().filter(p -> p.getStatus().equalsIgnoreCase("em preparo")).count();
                long finalizado = pedidos.stream().filter(p -> p.getStatus().equalsIgnoreCase("finalizado")).count();
                System.out.println("Pedidos em preparo: " + emPreparo);
                System.out.println("Pedidos finalizados: " + finalizado);
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void exportarRelatoriosParaArquivo() {
        try (FileWriter writer = new FileWriter("relatorios.txt")) {
            writer.write("Relatório de Pedidos:\n");
            pedidos.forEach(pedido -> {
                try {
                    writer.write(pedido.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Relatórios exportados para o arquivo 'relatorios.txt'.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar relatórios: " + e.getMessage());
        }
    }
}
