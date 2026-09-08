Aqui está uma sugestão de arquivo `README.md` estruturado para o seu repositório no GitHub, descrevendo o projeto com base nos arquivos fornecidos.

---

# Servidor HTTP Artesanal em Java

Este projeto consiste em uma implementação de um Servidor HTTP básico construído do zero em Java. Ele foi desenhado para entender os fundamentos da comunicação na web, sendo capaz de escutar requisições, servir páginas estáticas e processar envios de formulários via métodos GET e POST.

## 🚀 Funcionalidades

* Processamento de requisições HTTP brutas, identificando método e URL.


* Suporte à leitura do *Content-Length* e do corpo de requisições POST.


* Roteamento básico: requisições para a rota `/receptor` são processadas dinamicamente, enquanto outras URLs buscam por arquivos estáticos.


* Serviço de arquivos estáticos, permitindo renderizar documentos como `index.html` e `formularios.html` a partir de um diretório local.


* Processamento e extração de parâmetros enviados em *Query Strings* (GET) ou no corpo da requisição (POST).


* Geração dinâmica de uma página HTML contendo a lista dos parâmetros recebidos pelo servidor.


* Construção de respostas HTTP formatadas com *Status Code*, *Content-Type*, *Content-Length* e Data padrão RFC-1123.



## 📂 Estrutura do Projeto

O sistema está modularizado no pacote `Webapp` e organizado da seguinte forma:

### Núcleo do Servidor

* **`ServidorHTTP.java`**: É a classe principal que inicializa o *ServerSocket*, aceita as conexões TCP, lê as requisições e envia as respostas processadas.


* **`Requisicao.java`**: Extrai os dados essenciais de um documento bruto de requisição HTTP, como o método utilizado e a URL acessada.


* **`Resposta.java`**: Responsável por estruturar a resposta do servidor para o cliente, gerenciando cabeçalhos e formatando a saída de acordo com o protocolo HTTP/1.1.



### Gerenciamento de Páginas

* **`Pagina.java`**: Classe abstrata que define o contrato básico para as páginas através do método `getHtml()`.


* **`PaginaDoArquivo.java`**: Uma implementação de `Pagina` que busca e lê o conteúdo de um documento físico no disco, mapeando a raiz (`/`) para o arquivo `index.html`.


* **`Receptor.java`**: Uma página dinâmica que processa os parâmetros enviados pelo cliente (GET ou POST) e devolve um HTML formatado listando os dados recebidos.



### Recursos e Testes

* **Arquivos HTML**: Inclui o `index.html` de boas-vindas e o `formularios.html`, que possui vários testes práticos de formulários (busca de livros, cadastro de clientes, carrinho de compras).


* **Testes Unitários**: Scripts de teste para garantir a leitura correta das requisições (`RequisicaoTest.java`) e a formatação adequada da resposta bruta (`RespostaTest.java`).



## ⚠️ Configuração Importante

Antes de rodar a aplicação localmente, é crucial ajustar o diretório base dos arquivos estáticos.

* O arquivo `PaginaDoArquivo.java` possui um caminho de diretório definido de forma absoluta no seu construtor: `"C:\\progWeb_1\\AplicacaoWebArtesanal\\src\\Webapp"`.


* **Você precisará atualizar essa string** para refletir o caminho correto onde o projeto foi clonado no seu computador, para que as requisições encontrem os arquivos `index.html` e `formularios.html`.



## 💻 Como executar

1. Compile todos os arquivos `.java` do pacote `Webapp`.
2. Instancie e inicie a classe `ServidorHTTP`, informando a porta desejada (ex: `8080`) no método `iniciar(int porta)`.


3. O console exibirá: `"Servidor ouvindo na porta..."` e `"Acesse esta aplicação pelo endereço http://localhost..."`.


4. Abra seu navegador e acesse a raiz ou a URL `http://localhost:8080/formularios.html`.


5. Preencha os formulários e observe a rota `/receptor` processando e devolvendo os dados enviados.


6. Para encerrar o servidor no terminal, basta utilizar `Ctrl-C`.
