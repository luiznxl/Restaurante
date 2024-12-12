
public class Garcom {
    private String nome;
    private String codigo;
    private String telefone;
    private String email;

    public Garcom(String nome, String codigo, String telefone, String email) {
        this.nome = nome;
        this.codigo = codigo;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Garcom [nome=" + nome + ", codigo=" + codigo + ", telefone=" + telefone + ", email=" + email + "]";
    }
}
