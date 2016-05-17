package util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe responsável por gerar strings encriptadas.
 */
public class Hash {

  /**
   * Gerador de strings criptografadas no padrão MD5.
   *
   * @param value Valor a ser criptografado.
   * @return String hash.
   */
  private static final String doMD5Hash(final String value) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.reset();
      messageDigest.update(notNull(value).getBytes(Charset.forName("UTF8")));
      byte[] resultByte = messageDigest.digest();
      return Hex.encodeHexString(resultByte);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Algoritimo MD5 não disponível", e);
    }
  }

  /**
   * Gerador MD5 com complemento.
   *
   * @param value
   * @param salt
   * @return
   * @author arthemus
   */
  private static final String doMD5WithSalt(final String value, final String salt) {
    final String saltedAndHashed = notNull(value) + notNull(salt);
    return doMD5Hash(saltedAndHashed);
  }

  public static final String md5(final String value) {
    return doMD5Hash(value);
  }

  public static final String md5(final String value, final String salt) {
    return doMD5WithSalt(value, salt);
  }

  /**
   * Para impedir que valores Nulos sejam tratados pelos métodos de codificação.
   *
   * @param value
   * @return
   * @author arthemus
   */
  static final String notNull(String value) {
    return (value == null ? "" : value);
  }

  /**
   * <pre>
   * Codifica uma string alterando caracteres entre [A..Z], [a..z].
   * </pre>
   *
   * @param value Valor a ser encriptado.
   * @return Valor codificado.
   */
  public static final String rot13(String value) {
    final String temp = notNull(value);
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < temp.length(); i++) {
      char c = temp.charAt(i);
      if (c >= 'a' && c <= 'm') c += 13;
      else if (c >= 'A' && c <= 'M') c += 13;
      else if (c >= 'n' && c <= 'z') c -= 13;
      else if (c >= 'N' && c <= 'Z') c -= 13;
      result.append(c);
    }
    return result.toString();
  }

  /**
   * <pre>
   * Codifica uma string alterando caracteres dentro da tabela ASCII.
   * </pre>
   *
   * @param value Valor a ser encriptado.
   * @return Valor codificado.
   */
  public static final String rot47(String value) {
    final String temp = notNull(value);
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < temp.length(); i++) {
      char character = temp.charAt(i);
      if (character != ' ') {
        character += 47;
        if (character > '~') character -= 94;
      }
      result.append(character);
    }
    return result.toString();
  }

}
