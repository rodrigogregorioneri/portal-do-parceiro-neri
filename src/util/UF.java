package util;


/**
 * Enumeração que representa todas as UF do Brasil.
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */
 
public enum UF {
      
      AC("Acre"),  
	    AL("Alagoas"),  
	    AM("Amazonas"),  
	    AP("Amapá"),  
	    BA("Bahia"),  
	    CE("Ceará"),  
	    DF("Distrito Federal"),  
	    ES("Espirito Santo"),  
	    GO("Goias"),  
	    MA("Maranhão"),  
	    MG("Minas Gerais"),  
	    MS("Mato Grosso Sul"),  
	    MT("Mato Grosso"),  
	    PA("Pará"),  
	    PB("Paraiba"),  
	    PE("Pernanbuco"),  
	    PI("Piaui"),  
	    PR("Parana"),  
	    RJ("Rio de Janeiro"),  
	    RN("Rio Grande do Norte"),  
	    RO("Rondônia"),  
	    RR("Roraima"),  
	    RS("Rio Grande do Sul"),  
	    SC("Santa Catarina"),  
	    SE("Sergipe"),  
	    SP("São Paulo"),  
	    TO("Tocantins");  
	    String valor;
	    
	    UF( String valor ) {
	  	  this.valor = valor;
	    }
	    
	    @Override
    	public String toString(){
		    return valor;
	    }
}
