# TP2 — Desenvolvimento de Serviços Web e Testes com Java

## Objetivo
Sistema de cálculo de reembolso de consultas com **Java 21, Maven, JUnit 5 e Mockito**, cobrindo as 12 etapas do enunciado.

## IntelliJ IDEA
Abra a pasta clonada como projeto Maven, configure **Project SDK = JDK 21** e aguarde o Maven importar dependências. Execute a classe de testes `ReembolsoTest` ou use `mvn test` no terminal.

## Organização
- `src/main/java/br/edu/tp2/`: classes de produção e interfaces.
- `src/test/java/br/edu/tp2/ReembolsoTest.java`: 12 testes, um por etapa, identificados como `etapa01` a `etapa12`.
- `ConsultaHelper.java`: helper reutilizado nos testes.

## Etapas
1. Reembolso básico: R$ 200 × 70% = R$ 140.
2. Casos de borda (0, 100 e valores inválidos).
3. Classe isolada `CalculadoraReembolso`.
4. Dummy `Paciente`.
5. Fake `HistoricoConsultasFake`.
6. Stub `PlanoSaude`.
7. Spy `Auditoria`.
8. Mock `AutorizadorReembolso` com Mockito e rejeição.
9. Helper `ConsultaHelper` usado em vários testes.
10. Comparações com tolerância de 0,01.
11. Teto de R$ 150 por consulta.
12. Integração de stub, mock, helper, fake e auditoria.

## Execução
```bash
mvn test
```

## Resultado dos testes

A suíte `ReembolsoTest` foi executada no IntelliJ IDEA: **12 testes aprovados, 0 falhas**. A execução terminou com código de saída 0.
