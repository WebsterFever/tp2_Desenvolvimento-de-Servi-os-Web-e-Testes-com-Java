package br.edu.tp2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/** Testes das 12 etapas: dummy, fake, stub, spy, mock e integração. */
class ReembolsoTest {
 private final CalculadoraReembolso calc = new CalculadoraReembolso();
 /** Etapa 01: consulta de 200 com cobertura de 70% resulta em 140. */
 @Test void etapa01ReembolsoBasico() { assertProximo(140, calc.calcular(200,70)); }
 /** Etapa 02: valores e percentuais extremos. */
 @Test void etapa02CasosDeBorda() { assertProximo(0,calc.calcular(0,70)); assertProximo(0,calc.calcular(100,0)); assertProximo(100,calc.calcular(100,100)); assertThrows(IllegalArgumentException.class,()->calc.calcular(-1,50)); }
 /** Etapa 03: lógica isolada em CalculadoraReembolso. */
 @Test void etapa03ClasseIsolada() { assertProximo(50,calc.calcular(100,50)); }
 /** Etapa 04: dummy Paciente não altera o resultado. */
 @Test void etapa04DummyPaciente() { assertProximo(140,calc.calcular(new Paciente("Dummy"),200,70)); }
 /** Etapa 05: fake substitui persistência real. */
 @Test void etapa05FakeHistorico() { HistoricoConsultasFake fake=new HistoricoConsultasFake(); Consulta consulta=ConsultaHelper.criarPadrao(); fake.salvar(consulta); assertEquals(1,fake.listar().size()); assertEquals(consulta,fake.listar().get(0)); }
 /** Etapa 06: stubs retornam cobertura fixa. */
 @Test void etapa06StubPlano() { PlanoSaude plano50=()->50; PlanoSaude plano80=()->80; assertProximo(100,calc.calcular(200,plano50.percentualCobertura())); assertProximo(150,calc.calcular(200,plano80.percentualCobertura())); }
 /** Etapa 07: spy registra chamadas à auditoria. */
 @Test void etapa07SpyAuditoria() { Auditoria spy=spy(Auditoria.class); Consulta c=ConsultaHelper.criar(100); calc.registrarECalcular(c,()->50,new HistoricoConsultasFake(),spy,x->true); verify(spy,times(1)).registrarConsulta(c); }
 /** Etapa 08: mock nega autorização; sistema lança exceção e não grava. */
 @Test void etapa08MockAutorizacao() { AutorizadorReembolso mock=mock(AutorizadorReembolso.class); Consulta c=ConsultaHelper.criarPadrao(); when(mock.autorizar(c)).thenReturn(false); HistoricoConsultasFake historico=new HistoricoConsultasFake(); Auditoria auditoria=mock(Auditoria.class); assertThrows(IllegalStateException.class,()->calc.registrarECalcular(c,()->70,historico,auditoria,mock)); assertTrue(historico.listar().isEmpty()); verifyNoInteractions(auditoria); }
 /** Etapa 09: helper reutilizado nos testes 05, 07, 08 e 12. */
 @Test void etapa09HelperConsulta() { assertProximo(200,ConsultaHelper.criarPadrao().valor()); }
 /** Etapa 10: margem de erro de 0.01 para ponto flutuante. */
 @Test void etapa10MargemDeErro() { assertProximo(33.333333,calc.calcular(100,33.333333)); }
 /** Etapa 11: teto de R$ 150. */
 @Test void etapa11Teto() { assertProximo(150,calc.calcular(200,100)); assertProximo(150,calc.calcular(500,80)); assertProximo(140,calc.calcular(200,70)); }
 /** Etapa 12: integração de stub, mock, helper, fake e spy. */
 @Test void etapa12Integracao() { Consulta c=ConsultaHelper.criarPadrao(); PlanoSaude stub=()->80; AutorizadorReembolso autorizador=mock(AutorizadorReembolso.class); when(autorizador.autorizar(c)).thenReturn(true); HistoricoConsultasFake fake=new HistoricoConsultasFake(); Auditoria auditoria=mock(Auditoria.class); double recebido=calc.registrarECalcular(c,stub,fake,auditoria,autorizador); assertProximo(150,recebido); assertEquals(1,fake.listar().size()); verify(autorizador).autorizar(c); verify(auditoria).registrarConsulta(c); }
 private static void assertProximo(double esperado,double real) { assertEquals(esperado,real,0.01,"Diferença maior que R$ 0,01"); }
}
