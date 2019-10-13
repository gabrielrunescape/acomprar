package acomprar.gabrielrunescape.com.br.object;

import java.util.Date;
import android.util.Log;
import android.os.Parcel;
import android.os.Parcelable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Serializa o objeto do tipo Rendimento com suas propriedades.
 *
 * @author Gabriel Filipe
 * @version A0
 * @since 2017-07-29
 */

public class Rendimento implements Parcelable {
    private int ID;
    private double valor;
    private Date dt_compra;
    private Date dt_criacao;
    private String descricao;
    private Categoria categoria;

    // Recupera os dados novamente
    public static final Parcelable.Creator<Rendimento> CREATOR = new Parcelable.Creator<Rendimento>() {
        @Override
        public Rendimento createFromParcel(Parcel source) {
            return new Rendimento(source);
        }

        @Override
        public Rendimento[] newArray(int size) {
            return new Rendimento[size];
        }
    };

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
     * @param descricao Descrição do Rendimento
     * @param valor Valor do Rendimento.
     * @param categoria Catégoria do Rendimento.
     */
    public Rendimento(int id, String descricao, double valor, Categoria categoria) {
        this.ID = id;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    /**
     * Construtor com paramêtros
     *
     * @param id Código identificador do Rendimento.
     * @param valor Valor do Rendimento.
     * @param descricao Descrição do Rendimento.
     * @param compra Data de compra do Rendimento.
     * @param criacao Data de criacao do Rendimento.
     * @param categoria Catégoria do Rendimento.
     */
    public Rendimento(int id, String descricao, double valor, Date compra, Date criacao, Categoria categoria) {
        this.ID =  id;
        this.valor = valor;
        this.dt_compra = compra;
        this.dt_criacao = criacao;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    /**
     * Construtor com paramêtros para desserealizaão
     *
     * @param parcel Parcelable valores.
     */
    public Rendimento(Parcel parcel) {
        this.ID = parcel.readInt();
        this.descricao = parcel.readString();
        this.valor = parcel.readDouble();
        this.categoria = (Categoria) parcel.readValue(Categoria.class.getClassLoader());
        this.dt_criacao = (Date) parcel.readValue(Date.class.getClassLoader());
        this.dt_compra = (Date) parcel.readValue(Date.class.getClassLoader());
    }

    /**
     * @return Obtem a data no padrão ISO (Banco de dados).
     */
    public String getDateISO(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * @return Converte a data no padrão ISO (Banco de dados).
     */
    public Date getDateISO(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            return format.parse(date);
        } catch (ParseException ex) {
            Log.i("Rendimento.java: ", ex.getMessage());

            return null;
        }
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
     * @return Data de compra do Rendimento.
     */
    public Date getDT_Compra() { return dt_compra; }

    /**
     * @return Data de criacao do Rendimento.
     */
    public Date getDT_Criacao() { return dt_criacao; }

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
     * @param compra Define a data de compra do Rendimento.
     */
    public void setDT_vencimento(Date compra) { this.dt_compra = compra; }

    /**
     * @param criacao Define a data de criação do Rendimento.
     */
    public void setDT_lancamento(Date criacao) { this.dt_criacao = criacao; }

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
        String _return = "Rendimento {\n\tID: %d, \n\tDescricao: %s, \n\tValor: %1$s,\n\tDt_compra: %1$tY-%1$tm-%1$td\n\tDt_criacao: %1$tY-%1$tm-%1$td\n\t%s\n}";

        return String.format(_return, ID, descricao, valor, dt_compra, dt_criacao, categoria.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(descricao);
        dest.writeDouble(valor);
        dest.writeValue(categoria);
        dest.writeValue(dt_criacao);
        dest.writeValue(dt_compra);
    }
}
