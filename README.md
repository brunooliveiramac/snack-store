# snack-store
JQuery - Domain Driven Desing - Docker - Nginx reverse proxy - Terraform - Liquibase.

Pequena app utilizando as ferrametnas descritas :)
Pontos importantes:
  - No back ned foi utilizado <b>domain driven desing</b>, separado ao máximo possivel o domínio d aplicação de detalhes de 
  implementação como frameworks e banco por exemplo. A aplicação tem apenas um agragado. Essa separação é importante, pois 
  delimita o limite do business e assim podemos definir os microserviços futuramente :)
  - Para a execução é apenas necessario ter instalado <b>docker comunity e compose</b> em sua máquina. Para a execução execute
  o comando <b>docker-compose up</b> dentro de <b>monolitic-application</b>.
        Assim que executar o comando serão criados containers do banco, nginx para execução do proxy reverso na porta 80 e 
      o container da aplicação.
        O schema já será criado no warmup do container do banco e será populado pelo liquibase no warmup da aplicação.
        Após startado você pode acessar:
        
          http://localhost/index.html
          http://localhost/ingredients.html
          http://localhost/custom.html
          
          Faça seu pedido rs :)
          
  - Há também testes unitarios, integrados e end-to-end que também são executados no warmup da aplicação e que podem ser executados pelo comando:
          Dentro de monolitic-application/snacks:
            - mvn test
  - Os testes seguem o padrão sugerido por Mike Cohn, uma <b>pirâmide</b> onde a base é cmoposta por testes unitários de facil manutenção 
     e seguindo até o topo com testes integrados e end-to-end, mais caros e maior tempo de manutenção. Mais sobre:
     
     <a href="https://broliveira.cloud/2018/05/08/hands-on-test-pyramid-and-solid/">BROLIVEIRA!</a>     
       
  - Até o momento você tambem pode acessar a aplicação remotamente:
          <a href="http://ec2-18-206-67-116.compute-1.amazonaws.com/index.html">IFOME!</a>     
  - CI/CD Pipeline em construção.        
          
