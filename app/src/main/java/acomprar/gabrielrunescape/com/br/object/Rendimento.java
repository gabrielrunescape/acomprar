package acomprar.gabrielrunescape.com.br.object;

import java.util.Date;
import java.io.Serializable;

/**
 * Serializa o objeto do tipo Rendimento com suas propriedades.
 *
 * @author Gabriel Filipe
 * @version A1
 * @since 2017-07-29
 */

public class Rendimento implements Serializable {
    private int ID;
    private double valor;
    private Date dt_vencimento;
    private Date dt_lancamento;
    private Categoria categoria;

    /**
     * Construtor simples sem paramêtros.
     */
    public Rendimento() { }

    /**
     * Construtor com paramêtros
     *
     * @param id Código identificador do Rendimento.
     * @param valor Valor do Rendimento.
     */
    public Rendimento(int id, double valor) {
        this.ID = id;
        this.valor = valor;
    }

    /**
     * Construtor com paramêtros
     *
     * @param id Código identificador do Rendimento.
     * @param valor Valor do Eendimento.
     * @param categoria Catégoria do Rendimento.
     */
    public Rendimento(int id, double valor, Categoria categoria) {
        this.ID = id;
        this.valor = valor;
        this.categoria = categoria;
    }

    /**
     * Construtor com paramêtros
     *
     * @param id Código identificador do Rendimento.
     * @param valor Valor do Rendimento.
     * @param vencimento Data de vencimento do Rendimento.
     * @param lancamento Data de lançamento do Rendimento.
     * @param categoria Catégoria do Rendimento.
     */
    public Rendimento(int id, double valor, Date vencimento, Date lancamento, Categoria categoria) {
        this.ID =  id;
        this.valor = valor;
        this.categoria = categoria;
        this.dt_lancamento = lancamento;
        this.dt_vencimento = vencimento;
    }

    /**
     * @return Código identificador do Rendimento.
     */
    public int getID() { return ID; }

    /**
     * @return Valor do Rendimento.
     */
    public double getValor() { return valor; }

    /**
     * @return Data de vencimento do Rendimento.
     */
    public Date getDT_vencimento() { return dt_vencimento; }

    /**
     * @return Data de lançamento do Rendimento.
     */
    public Date getDT_lancamento() { return dt_lancamento; }

    /**
     * @return Catégoria do Rendimento.
     */
    public Categoria getCategoria() { return categoria; }

    /**
     * @param ID Define o código identificador do Rendimento.
     */
    public void setID(int ID) { this.ID = ID; }

    /**
     * @param valor Define o valor do Rendimento.
     */
    public void setValor(double valor) { this.valor = valor; }

    /**
     * @param dt_vencimento Define a data de vencimento do Rendimento.
     */
    public void setDT_vencimento(Date dt_vencimento) { this.dt_vencimento = dt_vencimento; }

    /**
     * @param dt_lancamento Define a data de lançamento do Rendimento.
     */
    public void setDT_lancamento(Date dt_lancamento) { this.dt_lancamento = dt_lancamento; }

    /**
     * @param categoria Define a catégoria do Rendimento.
     */
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    /**
     * Sobreescreve a função para retornar uma String do Objeto.
     *
     * @return String do objeto no formato JSON.
     */
    public String toString() {
        String _return = "Rendimento {\n\tID: %d, \n\tValor: %1$s,\n\tdt_lancamento: %1$tY-%1$tm-%1$td\n\tdt_vencimento: %1$tY-%1$tm-%1$td\n\t%s\n}";

        return String.format(_return, ID, valor, dt_lancamento, dt_vencimento, categoria.toString());
    }
}
