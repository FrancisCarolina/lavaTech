package ifpr.pgua.eic.tarefas.model.entities;

public class Cliente {
  private int id;
  private String nome;
  private String contato;
  private LavaCar lavaCar;

  public Cliente(String nome, String contato, LavaCar lavaCar) {
    this.nome = nome;
    this.contato = contato;
    this.lavaCar = lavaCar;
  }

  public Cliente(int id, String nome, String contato, LavaCar lavaCar) {
    this.id = id;
    this.nome = nome;
    this.contato = contato;
    this.lavaCar = lavaCar;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getContato() {
    return contato;
  }

  public void setContato(String contato) {
    this.contato = contato;
  }

  @Override
  public String toString() {
    return "" + nome;
  }

  public LavaCar getLavaCar() {
    return lavaCar;
  }

  public void setLavaCar(LavaCar lavaCar) {
    this.lavaCar = lavaCar;
  }

}
