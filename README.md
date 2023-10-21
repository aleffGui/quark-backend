# quark-backend

Essa API consiste em gerenciar tarefas, onde o usuário pode cadastrar, editar e excluir tarefas. Existem dois tipos de usuários: ADMIN e USER. O ADMIN, além do gerenciamento das tarefas, também pode gerenciar os usuários do sistema. Também foi implementado autenticação jwt com spring Security para proteger as requisições.

Para logar como usuário admin, basta inserir os dados:
Username: guilhermeferreira,
Password: “123456”

Para executar o projeto rode os seguintes comandos:

1) Configuração do banco de dados:
Instalar postgresql:
https://www.postgresql.org/download/

Criar uma base de dados com o nome: quarkapp

2) Clonar o projeto:
Git clone https://github.com/aleffGui/quark-backend.git

3) Instalar a versão 11 do Java:

https://adoptium.net/temurin/releases/?version=11

3.1) Verificar a versão do java:
java -version

3.2) Instalar o maven:

https://maven.apache.org/download.cgi

3.3) Após concluir as instalações, abrir o terminal e navegar até a pasta raíz do projeto

3.4) Rodar o comando:
mvn clean install

3.5) Por fim, executar o comando:
mvn spring-boot:run
