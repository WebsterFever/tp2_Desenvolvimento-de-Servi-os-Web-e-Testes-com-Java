package br.edu.tp2;
/** Helper: centraliza dados padrão para evitar repetição nos testes. */
public final class ConsultaHelper {
 private ConsultaHelper() {}
 public static Consulta criar(double valor) { return new Consulta(new Paciente("Paciente de teste"), valor); }
 public static Consulta criarPadrao() { return criar(200.0); }
}
