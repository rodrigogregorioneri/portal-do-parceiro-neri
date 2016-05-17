package util;


/**
 * Enumeração que representa o status de uma oportunidade.
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */
 
public enum StatusGeral {
    
    APROVADO("Aprovado"),
    REPROVADO("Reprovado");
    String valor;
    
    StatusGeral( String valor ){
      this.valor = valor;
    }
    
    @Override 
    public String toString(){
      return valor;
    }
    
}
