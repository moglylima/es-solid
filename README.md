# Sistema Esportivo Educacional - Versão Monolítica (Não-SOLID)

## ⚠️ AVISO IMPORTANTE
**Esta implementação INTENCIONALMENTE viola os princípios SOLID para fins educacionais!**

## 📋 Descrição
API REST em Java (Spring Boot) para gestão de conteúdos esportivos educacionais. Esta versão concentra todas as responsabilidades em uma única classe, demonstrando os problemas de uma arquitetura monolítica.

## 🎯 Entidades Implementadas

### Esporte
- `id`: Identificador único
- `nome`: Nome da modalidade (Vôlei, Basquete, etc.)
- `categoria`: Tipo de esporte (Coletivo, Individual)

### Conteúdo
- `id`: Identificador único
- `titulo`: Título do material educacional
- `url`: Link para vídeo/PDF
- `nivel`: Nível educacional (Fundamental II/Médio)
- `esporteId`: Relacionamento com Esporte

### Professor
- `id`: Identificador único
- `nome`: Nome completo
- `especializacao`: Área de especialização

### Categoria
- `id`: Identificador único
- `nome`: Nome da categoria (Aquáticos, Quadra, Academia)

### Aula
- `id`: Identificador único
- `data`: Data da aula
- `duracao`: Duração em minutos
- `conteudos`: Lista de IDs de conteúdos
- `professorId`: ID do professor responsável

## 🚀 Como Executar

```bash
# Navegar até o diretório
cd sistema-esportivo-monolitico

# Executar com Maven
mvn spring-boot:run

# Ou compilar e executar
mvn clean package
java -jar target/sistema-esportivo-monolitico-1.0.0.jar
```

A API estará disponível em: `http://localhost:8080/api`

## 📚 Endpoints Implementados

### Esportes
- `POST /api/esportes` - Cadastra novo esporte
- `GET /api/esportes` - Lista todos os esportes

### Conteúdos
- `POST /api/conteudos` - Adiciona conteúdo vinculado a esporte
- `GET /api/conteudos/esporte/{esporteId}` - Lista conteúdos de um esporte

### Professores
- `POST /api/professores` - Cadastra novo professor
- `GET /api/professores/especializacao/{especializacao}` - Lista professores por especialização

### Aulas
- `POST /api/aulas` - Cria aula com conteúdos e professor
- `GET /api/aulas/filtrar?categoria={categoria}` - Filtra aulas por categoria do esporte

### Debug
- `GET /api/debug/status` - Status da aplicação

## 🔥 Problemas Desta Implementação

### 1. Violação do Single Responsibility Principle (SRP)
A classe `SistemaEsportivoController` possui MÚLTIPLAS responsabilidades:
- Controle de requisições HTTP
- Lógica de negócio
- Persistência de dados
- Validações
- Filtros e consultas

### 2. Violação do Open/Closed Principle (OCP)
- Para adicionar nova funcionalidade, é necessário modificar a classe principal
- Não é extensível sem modificação do código existente

### 3. Violação do Dependency Inversion Principle (DIP)
- Depende diretamente de implementações concretas (HashMap)
- Não utiliza abstrações ou interfaces

### 4. Problemas Adicionais
- **Código duplicado**: Validações repetitivas
- **Difícil manutenção**: Tudo em uma classe
- **Testes complexos**: Impossível testar partes isoladas
- **Alto acoplamento**: Componentes fortemente interligados
- **Baixa coesão**: Responsabilidades misturadas

## 📝 Exemplos de Uso

### Criar Esporte
```bash
curl -X POST http://localhost:8080/api/esportes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Futebol",
    "categoria": "Coletivo"
  }'
```

### Criar Conteúdo
```bash
curl -X POST http://localhost:8080/api/conteudos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Fundamentos do Futebol",
    "url": "https://example.com/futebol.mp4",
    "nivel": "Fundamental II",
    "esporteId": 1
  }'
```

### Criar Professor
```bash
curl -X POST http://localhost:8080/api/professores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Carlos Educador",
    "especializacao": "Esportes Coletivos"
  }'
```

### Criar Aula
```bash
curl -X POST http://localhost:8080/api/aulas \
  -H "Content-Type: application/json" \
  -d '{
    "data": "2025-05-27",
    "duracao": 90,
    "conteudos": [1],
    "professorId": 1
  }'
```

## 🔍 Dados de Exemplo
A aplicação inicializa com dados de exemplo:
- 3 esportes (Vôlei, Basquete, Natação)
- 2 professores
- 2 conteúdos de vôlei
- 1 aula

## 🎓 Objetivo Educacional
Esta implementação serve como exemplo do que **NÃO FAZER** em um projeto real. Demonstra os problemas de:
- Código monolítico
- Violação de princípios SOLID
- Falta de separação de responsabilidades
- Dificuldades de manutenção e teste

## ➡️ Próximo Passo
Veja a implementação SOLID na pasta `../sistema-esportivo-solid/` para comparar as abordagens e entender as melhorias.
