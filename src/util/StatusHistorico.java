package util;

/**
 * Enumeração que representa o status de cada atualização de uma oportunidade;
 * 
 * @author Leonardo Cardena
 * @version 1.0
 * @since 2016-05-10
 *
 */

public enum StatusHistorico {

	ATIVO("Ativo"), 
	PERDIDO("Perdido"), 
	GANHO("Ganho");
	String valor;

	StatusHistorico(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}
	
}
