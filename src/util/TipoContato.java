package util;

/**
 * Enumeração que representa o tipo de contato de um parceiro.
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */

public enum TipoContato {

	PRINCIPAL("Principal"),
	SECUNDARIO("Secundário");
	String valor;

	TipoContato(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}

}
