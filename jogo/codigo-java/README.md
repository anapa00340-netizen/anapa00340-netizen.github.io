# Batalha por turnos em Java

Informe a vida inicial do herói e do inimigo. O herói causa 20 de dano e o inimigo responde com 15 se ainda estiver vivo. Digite 1 para atacar.

## Rodar

Use Java 21. No Eclipse, execute a classe `projeto01poo.PrincipalMain`. Pelo terminal, na raiz do projeto:

```powershell
$fontes = Get-ChildItem src/projeto01poo -Filter *.java | Select-Object -ExpandProperty FullName
javac -encoding UTF-8 -d out $fontes
java -cp out projeto01poo.PrincipalMain
```

O banco é opcional. Para gravar o vencedor, inclua o driver JDBC no classpath e configure as variáveis `JOGO_JDBC_URL`, `JOGO_JDBC_USUARIO` e `JOGO_JDBC_SENHA`. A operação usa a tabela `resultado`, com a coluna `Vencedor`. A classe `projeto01poo.TesteConexao` verifica a conexão.
