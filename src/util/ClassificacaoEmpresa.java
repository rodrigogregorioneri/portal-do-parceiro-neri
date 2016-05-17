package util;

/**
 * Enumeração que representa o porte da empresa.
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */

public enum ClassificacaoEmpresa {
	
	PEQUENA("Pequena"), 
	GRANDE("Grande"), 
	MEDIA("Média");
	String valor;

	ClassificacaoEmpresa(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}

}
