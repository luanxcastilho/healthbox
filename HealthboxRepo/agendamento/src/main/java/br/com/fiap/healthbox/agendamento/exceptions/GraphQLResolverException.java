package br.com.fiap.healthbox.agendamento.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GraphQLResolverException extends DataFetcherExceptionResolverAdapter {
    
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        Map<String, Object> extensions = new HashMap<>();
        
        if (ex instanceof EnfermeiroNotFoundException) {
            extensions.put("classification", "NotFound");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof MedicoNotFoundException) {
            extensions.put("classification", "NotFound");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof PacienteNotFoundException) {
            extensions.put("classification", "NotFound");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof AgendamentoNotFoundException) {
            extensions.put("classification", "NotFound");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof EnfermeiroPossuiAgendamentoException) {
            extensions.put("classification", "InternalError");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof MedicoPossuiAgendamentoException) {
            extensions.put("classification", "InternalError");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        if (ex instanceof PacientePossuiAgendamentoException) {
            extensions.put("classification", "InternalError");
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .extensions(extensions)
                    .build();
        }
        
        
        extensions.put("classification", "InternalError");
        return GraphqlErrorBuilder.newError(env)
                .message("Ocorreu um erro inesperado: " + ex.getMessage())
                .errorType(graphql.ErrorType.DataFetchingException)
                .extensions(extensions)
                .build();
    }
}
