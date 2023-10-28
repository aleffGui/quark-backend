# Backend do TaskManager
## Para acessar o projeto completo, [clique aqui](https://quark-taskmanager.netlify.app)
  - OBS: Pode haver um tempo de espera de até 5 minutos após realizar o login, pois o deploy da API foi realizado no Render e está sendo utilizado o plano gratuito que desliga o servidor após 15 minutos de inatividade da API. Passado os 15 minutos, o servidor só iniciará novamente após uma nova requisição!
## Descrição da API de Gerenciamento de Tarefas

A API em questão é um sistema de gerenciamento de tarefas projetado para permitir que os usuários cadastrem, editem, excluam e marquem como concluídas as tarefas. Além disso, a aplicação oferece funcionalidades de filtragem para facilitar a busca por tarefas específicas.

### Tipos de Usuários

A aplicação suporta dois tipos de usuários:

- **ADMIN:** Os administradores têm amplos poderes, incluindo o gerenciamento de tarefas e a capacidade de gerenciar os próprios usuários do sistema.

- **USER:** Os usuários comuns têm a capacidade de criar, editar e gerenciar as tarefas.

### Segurança e Autenticação

Para garantir a segurança e a autenticação, a API implementa autenticação JWT (JSON Web Tokens) usando o Spring Security. Isso ajuda a proteger as requisições e garante que apenas os usuários autenticados tenham acesso às funcionalidades do sistema.

Para logar como usuário admin, basta inserir os dados:

Username: guilhermeferreira

Password: 123456

## Configurações para instalação do projeto localmente:

- Configuração do banco de dados:
  - [Instalar postgresql](https://www.postgresql.org/download/)
  - Iniciar o servidor do postgres
  - Criar uma base de dados com o nome: quarkapp

- Clonar o projeto, utilizando o comando abaixo
```
git clone https://github.com/aleffGui/quark-backend.git
```

- [Instalar a versão 17 do Java](https://adoptium.net/temurin/releases/?version=17)

- Verificar a versão do java, utilizando o comando abaixo:
```
java -version
```
- [Instalar o maven](https://maven.apache.org/download.cgi)

- Após concluir as instalações, abrir o terminal e navegar até a pasta raíz do projeto

-  Na pasta raíz do projeto, executar o comando abaixo:
```
mvn clean install
```
- Por fim, executar o comando abaixo:
```
mvn spring-boot:run
```
