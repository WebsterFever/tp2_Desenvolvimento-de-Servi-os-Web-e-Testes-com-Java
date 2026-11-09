package br.edu.tp2;
public record Consulta(Paciente paciente, double valor) {
    public Consulta { if (paciente == null) throw new IllegalArgumentException("Paciente obrigatório"); if (!Double.isFinite(valor) || valor < 0) throw new IllegalArgumentException("Valor inválido"); }
}
