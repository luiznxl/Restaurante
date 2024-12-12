
import java.util.Date;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private Garcom garcom;
    private List<Prato> pratos;
    private Date data;
    private String status;

    public Pedido(Cliente cliente, Garcom garcom, List<Prato> pratos, Date data, String status) {
        this.cliente = cliente;
        this.garcom = garcom;
        this.pratos = pratos;
        this.data = data;
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public void setGarcom(Garcom garcom) {
        this.garcom = garcom;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente + ", garcom=" + garcom + ", pratos=" + pratos + ", data=" + data + ", status=" + status + "]";
    }
}
