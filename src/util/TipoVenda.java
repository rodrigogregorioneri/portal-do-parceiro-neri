package util;


/**
 * Enumeração que representa o tipo da venda.
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */
 
public enum TipoVenda {
    
    NOVA_VENDA("Nova Venda");
    String valor;
    
    TipoVenda( String valor ){
      this.valor = valor;
    }
    
    @Override 
    public String toString(){
      return valor;
    }
}
