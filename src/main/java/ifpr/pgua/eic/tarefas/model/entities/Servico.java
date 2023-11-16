package ifpr.pgua.eic.tarefas.model.entities;

import java.time.LocalDate;
import java.util.Date;

public class Servico {
    private int id;
    private Cliente cliente;
    private LavaCar lavacar;
    private Tipo tipo;
    private float custo;
    private boolean efetuado;
    private boolean pago;
    private LocalDate dataAgendamento;

    public Servico(int id, Cliente cliente, LavaCar lavacar, Tipo tipo, float custo, boolean efetuado, boolean pago,
            LocalDate dataAgendamento) {
        this.id = id;
        this.cliente = cliente;
        this.lavacar = lavacar;
        this.tipo = tipo;
        this.custo = custo;
        this.efetuado = efetuado;
        this.pago = pago;
        this.dataAgendamento = dataAgendamento;
    }

    public Servico(Cliente cliente, LavaCar lavacar, Tipo tipo, float custo, boolean efetuado, boolean pago,
            LocalDate dataAgendamento) {
        this.cliente = cliente;
        this.lavacar = lavacar;
        this.tipo = tipo;
        this.custo = custo;
        this.efetuado = efetuado;
        this.pago = pago;
        this.dataAgendamento = dataAgendamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LavaCar getLavacar() {
        return lavacar;
    }

    public void setLavacar(LavaCar lavacar) {
        this.lavacar = lavacar;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public boolean isEfetuado() {
        return efetuado;
    }

    public void setEfetuado(boolean efetuado) {
        this.efetuado = efetuado;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
