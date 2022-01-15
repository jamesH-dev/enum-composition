package exercise03.application;


import exercise03.entities.Client;
import exercise03.entities.Order;
import exercise03.entities.OrderItem;
import exercise03.entities.Product;
import exercise03.entities.enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Ler os dados de um pedido com N itens (N fornecido pelo usuário). Depois, mostrar um
 * sumário do pedido conforme exemplo (próxima página). Nota: o instante do pedido deve ser
 * o instante do sistema: new Date()
 */
public class Application {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        System.out.println("Informe os dados do cliente:");
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Data de Nascimento (DD/MM/AAA): ");
        Date birthDate = sdf.parse(sc.next());
        Client client = new Client(name, email, birthDate);

        System.out.println("\nInforme os dados do pedido:");
        System.out.print("Status: ");
        OrderStatus status = OrderStatus.valueOf(sc.next());
        Order order = new Order(new Date(), status, client);

        System.out.print("Quantos itens terão neste pedido? ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++){
            System.out.println("\nPedido #" + i);
            System.out.print("Nome do Produto: ");
            sc.nextLine();
            String productName = sc.nextLine();
            System.out.print("Preço do Produto: ");
            double productPrice = sc.nextDouble();
            Product product = new Product(productName, productPrice);
            System.out.print("Quantidade: ");
            int quantity = sc.nextInt();

            order.addItem(new OrderItem(quantity, productPrice, product));
        }

        System.out.println("\nRESUMO DO PEDIDO");
        System.out.println("Momento da Compra: " + sdtf.format(order.getMoment()));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Cliente: " + client);
        System.out.println("Itens do pedido:");
        for (OrderItem o : order.getItems()){
            System.out.println(o.getProduct().getName() + " - R$ " + String.format("%.2f", o.getPrice())
                + ", Quantidade: " + o.getQuantity()
                + ", Subtotal: R$ " + String.format("%.2f", o.subTotal()));
        }
        System.out.println("Valor Total: R$ " + String.format("%.2f", order.total()));



        sc.close();
    }
}
