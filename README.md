# quark-backend

Essa API consiste em gerenciar tarefas, onde o usuário pode cadastrar, editar e excluir tarefas. Existem dois tipos de usuários: ADMIN e USER. O ADMIN, além do gerenciamento das tarefas, também pode gerenciar os usuários do sistema. Também foi implementado autenticação jwt com spring Security para proteger as requisições.

Para logar como usuário admin, basta inserir os dados:
Username: guilhermeferreira
Password: 123456

## Configurações para instalação do projeto:

- Configuração do banco de dados:
  - [Instalar postgresql](https://www.postgresql.org/download/){:target="_blank"}
  - Iniciar o servidor do postgres
  - Criar uma base de dados com o nome: quarkapp

- Clonar o projeto, utilizando o comando abaixo
```
git clone https://github.com/aleffGui/quark-backend.git
```

- [Instalar a versão 11 do Java](https://adoptium.net/temurin/releases/?version=11){:target="_blank"}

- Verificar a versão do java, utilizando o comando abaixo:
```
java -version
```
- [Instalar o maven](https://maven.apache.org/download.cgi){:target="_blank"}

- Após concluir as instalações, abrir o terminal e navegar até a pasta raíz do projeto

-  Na pasta raíz do projeto, executar o comando abaixo:
```
mvn clean install
```
- Por fim, executar o comando abaixo:
```
mvn spring-boot:run
```
