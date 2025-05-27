# Healthbox

Sistema de agendamento de consultas médicas, construído com arquitetura de microserviços utilizando **Spring Boot**, **GraphQL** para API, **Kafka** para mensageria/eventos e **MySQL** como banco de dados, para o FIAP - Tech Challenge 3.

## Tabela de Conteúdos

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Como Rodar](#como-rodar)
- [Kafka - Eventos](#kafka---eventos)


## Visão Geral

Este projeto tem como objetivo gerenciar o agendamento de consultas, pacientes, médicos e enfermeiros, com integração de notificações automáticas usando Kafka.


## Funcionalidades

- Cadastro e atualização de pacientes, médicos e enfermeiros
- Agendamento, atualização e exclusão de consultas
- Notificações via Kafka para confirmação e lembretes
- API GraphQL para todas as operações principais


## Arquitetura

- **Backend**: Java 21, Spring Boot, API GraphQL
- **Mensageria**: Apache Kafka para eventos de agendamento/notificações
- **Banco de Dados**: MySQL


## Tecnologias

- Java 21
- Spring Boot
- GraphQL
- Apache Kafka
- MySQL
- Docker

## Como Rodar

### Pré-requisitos

- Docker Desktop
- WSL

### Subindo o ambiente

```bash
# Repositório do projeto: 
https://github.com/luanxcastilho/healthbox

# Na raiz do projeto, execute o comando abaixo para realizar o build:
mvn clean package -DskipTests

# Na raiz do projeto, execute o comando abaixo para subir os serviços:
docker-compose up -d
```

- O serviço estará disponível por padrão em: `http://localhost:8081/graphql`

## Kafka - Eventos

Os eventos são publicados em tópicos Kafka, como `agendamento-criado`, `agendamento-atualizado` pelo serviço de agendamento, e esses mesmos tópicos são consumidos pelo serviço de notificação.
