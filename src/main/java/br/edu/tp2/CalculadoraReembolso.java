package br.edu.tp2;
/** Regras de reembolso. Percentual de cobertura de 0 a 100. Teto de R$ 150. */
public class CalculadoraReembolso {
 public static final double TETO = 150.0;
 public double calcular(double valor, double percentual) {
  if (!Double.isFinite(valor) || valor < 0) throw new IllegalArgumentException("Valor inválido");
  if (!Double.isFinite(percentual) || percentual < 0 || percentual > 100) throw new IllegalArgumentException("Percentual inválido");
  return Math.min(valor * percentual / 100.0, TETO);
 }
 public double calcular(Paciente paciente, double valor, double percentual) {
  if (paciente == null) throw new IllegalArgumentException("Paciente obrigatório");
  return calcular(valor, percentual);
 }
 public double registrarECalcular(Consulta consulta, PlanoSaude plano, HistoricoConsultas historico, Auditoria auditoria, AutorizadorReembolso autorizador) {
  if (consulta == null || plano == null || historico == null || auditoria == null || autorizador == null) throw new IllegalArgumentException("Dependência obrigatória");
  if (!autorizador.autorizar(consulta)) throw new IllegalStateException("Reembolso não autorizado");
  double valor = calcular(consulta.paciente(), consulta.valor(), plano.percentualCobertura());
  historico.salvar(consulta);
  auditoria.registrarConsulta(consulta);
  return valor;
 }
}
