package acomprar.gabrielrunescape.com.br.object;

import java.io.Serializable;

/**
 * Serializa o objeto do tipo Categoria com suas propriedades.
 *
 * @author Gabriel Filipe
 * @version A1
 * @since 2017-07-29
 */
public class Categoria  implements Serializable {
    private int ID;
    private String nome;
    private String tipo;

    /**
     * Construtor simples sem paramêtros
     */
    public Categoria() { }

    /**
     * Construtor da classe com paramêtros
     *
     * @param id Código identificador da Categoria
     * @param nome Nome da Categoria
     */
    public Categoria(int id, String nome) { this.ID = id; this.nome = nome; }

    /**
     * Construtor da classe com paramêtros
     *
     * @param nome Nome da Categoria
     * @param tipo Tipo da Categoria
     */
    public Categoria(String nome, String tipo) { this.nome = nome; this.tipo = tipo; }

    /**
     * Construtor da classe com paramêtros
     *
     * @param id Código identificador da Categoria
     * @param nome Nome da Categoria
     * @param tipo Tipo da Categoria
     */
    public Categoria(int id, String nome, String tipo) { this.ID = id; this.nome = nome; this.tipo = tipo; }

    /**
     * @return Código identificador da Categoria.
     */
    public int getID() { return ID; }

    /**
     * @return Tipo da Categoria.
     */
    public String getTipo() { return tipo; }

    /**
     * @return Nome da Categoria.
     */
    public String getNome() { return nome; }

    /**
     * @param ID Define o código identificador da Categoria.
     */
    public void setID(int ID) { this.ID = ID; }

    /**
     * @param tipo Define o tipo da Categoria.
     */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * @param nome Define o nome da Categoria.
     */
    public void setNome(String nome) { this.nome = nome; }

    @Override
    /**
     * Sobreescreve a função para retornar uma String do Objeto.
     *
     * @return String do objeto no formato JSON.
     */
    public String toString() {
        String _return = "Categoria {\n\tID: %d, \n\tNome: %s,\n\tTipo: %s\n}";

        return String.format(_return, ID, nome, tipo);
    }
}
