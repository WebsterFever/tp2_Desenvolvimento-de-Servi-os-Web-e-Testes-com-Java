package br.edu.tp2;
import java.util.ArrayList;
import java.util.List;
/** Fake: armazena consultas em memória, sem banco real. */
public class HistoricoConsultasFake implements HistoricoConsultas {
 private final List<Consulta> consultas = new ArrayList<>();
 public void salvar(Consulta consulta) { consultas.add(consulta); }
 public List<Consulta> listar() { return List.copyOf(consultas); }
}
